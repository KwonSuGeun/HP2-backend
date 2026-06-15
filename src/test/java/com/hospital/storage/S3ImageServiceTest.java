package com.hospital.storage;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class S3ImageServiceTest {

    private HttpServer httpServer;
    private S3ImageService s3ImageService;

    private final AtomicReference<String> requestMethod = new AtomicReference<>();
    private final AtomicReference<String> requestPath = new AtomicReference<>();
    private final AtomicReference<Integer> requestBodySize = new AtomicReference<>();

    @BeforeEach
    void setUp() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(0), 0);
        httpServer.createContext("/", this::handleRequest);
        httpServer.start();

        int port = httpServer.getAddress().getPort();
        s3ImageService = new S3ImageService();
        ReflectionTestUtils.setField(s3ImageService, "s3Endpoint", "http://localhost:" + port);
        ReflectionTestUtils.setField(s3ImageService, "bucketName", "emp_photo");
    }

    @AfterEach
    void tearDown() {
        httpServer.stop(0);
    }

    private void handleRequest(HttpExchange exchange) throws IOException {
        requestMethod.set(exchange.getRequestMethod());
        requestPath.set(exchange.getRequestURI().getPath());
        requestBodySize.set(exchange.getRequestBody().readAllBytes().length);
        exchange.sendResponseHeaders(200, -1);
        exchange.close();
    }

    @Test
    void upload_sendsPutToSeaweedFsAndReturnsKey() throws IOException {
        byte[] jpeg = new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xD9};
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "staff-photo.jpg",
                "image/jpeg",
                jpeg
        );

        String key = s3ImageService.upload(file);

        assertTrue(key.endsWith(".jpg"));
        assertEquals("PUT", requestMethod.get());
        assertEquals("/emp_photo/" + key, requestPath.get());
        assertEquals(jpeg.length, requestBodySize.get());
    }
}
