package com.example.tasteofkorea.service;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucket;

    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + changedImageName(uploadFile.getName());
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile); // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        return uploadImageUrl; // 업로드된 파일의 S3 URL 주소 반환
    }

    // 실질적인 s3 업로드 부분
    private String putS3(File uploadFile, String fileName) {
        // 확장자 추출
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String contentType;

        // 확장자에 맞는 MIME 타입 설정
        switch (extension) {
            case "jpg":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "gif":
                contentType = "image/gif";
                break;
            case "bmp":
                contentType = "image/bmp";
                break;
            case "webp":
                contentType = "image/webp";
                break;
            default:
                contentType = "application/octet-stream";  // 기본 MIME 타입
                break;
        }

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);

        // 파일 업로드
        PutObjectRequest request = new PutObjectRequest(bucket, fileName, uploadFile)
                .withMetadata(metadata);  // 콘텐츠 타입 설정

        amazonS3Client.putObject(request);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }




    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    // 랜덤 파일 이름 메서드 (파일 이름 중복 방지)
    private String changedImageName(String originName) {
        String random = UUID.randomUUID().toString();
        return random + originName;
    }

    public String uploadFromUrl(String imageUrl, String fileName, String dirName) throws IOException {
        URL url = new URL(imageUrl);
        File tempFile = File.createTempFile("download_", ".tmp");

        try (InputStream in = url.openStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        String filePath = dirName + "/" + fileName;
        String uploadedUrl = putS3(tempFile, filePath);
        removeNewFile(tempFile);
        return uploadedUrl;
    }


}
