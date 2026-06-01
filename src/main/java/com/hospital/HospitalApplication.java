package com.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** HIS 백엔드 진입점. 기본 포트 8081 (application.yml 참고) */
@SpringBootApplication
@MapperScan("com.hospital.menu")
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }
}
