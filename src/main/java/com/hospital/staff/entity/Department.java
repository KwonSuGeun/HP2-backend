package com.hospital.staff.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
