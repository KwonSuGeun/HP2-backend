package com.hospital.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "HOSPITAL", name = "STAFF_DEPARTMENT")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private String departmentId;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "STAFF_EXTENSION_NO")
    private String staffExtensionNo;

    /**
     * 부서 1 : 직원 N. 연관관계의 주인은 Staff.department(FK 보유)이고,
     * 이쪽은 mappedBy로 읽기 전용 매핑이다.
     */
    @OneToMany(mappedBy = "department")
    private List<Staff> staffList = new ArrayList<>();
}
