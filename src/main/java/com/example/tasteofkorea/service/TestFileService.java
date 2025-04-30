package com.example.tasteofkorea.service;

import com.example.tasteofkorea.entity.TestFileEntity;
import com.example.tasteofkorea.repository.TestFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestFileService {

    private final S3Uploader s3Uploader;
    private final TestFileRepository testFileRepository;


    private final TestFileRepository testFileEntityRepository;
    public void saveFile(MultipartFile file) throws IOException {
        // S3 업로드
        String uploadedUrl = s3Uploader.upload(file, "images");

        // DB 저장
        TestFileEntity entity = new TestFileEntity();
        entity.setImgUrl(uploadedUrl);

        testFileRepository.save(entity); // 파일 정보 DB 저장
    }


    public TestFileEntity getFile(int id) {
        return testFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 파일이 없습니다."));
    }


}
