package com.hospital.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3ImageService {

    private final RestClient restClient = RestClient.create();

    @Value("${seaweedfs.s3.endpoint}")
    private String s3Endpoint;

    @Value("${seaweedfs.s3.bucket}")
    private String bucketName;

    public String upload(MultipartFile file) throws IOException {
        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf('.')).toLowerCase();
        String key = UUID.randomUUID().toString().replace("-", "") + ext;

        String contentType = file.getContentType();

        restClient.put()
                .uri(s3Endpoint + "/" + bucketName + "/" + key)
                .contentType(MediaType.parseMediaType(contentType))
                .body(file.getBytes())
                .retrieve()
                .toBodilessEntity();

        return key;
    }
}
