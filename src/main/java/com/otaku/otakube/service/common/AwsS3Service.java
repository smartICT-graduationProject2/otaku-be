package com.otaku.otakube.service.common;

import com.otaku.otakube.common.utils.imageFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AwsS3Service {
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${spring.cdn.url}")
    private String cloudFrontUrl;

    public String uploadFile(MultipartFile multipartFile) {

        if(multipartFile.isEmpty()) {
            log.info("image is null");
            return "";
        }

        String fileName = getFileName(multipartFile);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType(multipartFile.getContentType())
                    .contentLength(multipartFile.getSize())
                    .key(fileName)
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(multipartFile.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            log.error("cannot upload image",e);
            throw new RuntimeException(e);
        }

        return cloudFrontUrl + fileName;
    }

    public String getFileName(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) return "";
        return imageFileUtils.buildFileName(multipartFile.getOriginalFilename());
    }
}