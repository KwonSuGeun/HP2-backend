package com.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 백엔드 진입점.
 *
 * <p>기동 시:
 * <ul>
 *   <li>server.port 8081 (application.yml) — 프론트 Axios baseURL 과 일치</li>
 *   <li>@MapperScan → MenuMapper 인터페이스 + MenuMapper.xml 연결</li>
 * </ul>
 *
 * <p>사이드바 API: GET http://localhost:8081/api/menus → MenuController
 */
@SpringBootApplication
@MapperScan("com.hospital.menu")
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
}
