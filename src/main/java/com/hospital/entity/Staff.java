package com.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "HOSPITAL", name = "HIS_STAFF_MST")
public class Staff {

    @Id
    @Column(name = "STAFF_ID")
    private String staffId;

    @Column(name = "STAFF_PASSWORD")
    private String staffPassword;

    @Column(name = "STAFF_NAME")
    private String staffName;

    @Column(name = "STAFF_TYPE")
    private String staffType;

    @Column(name = "STAFF_ROLE_CODE")
    private String staffRoleCode;

    @Column(name = "STAFF_DEPARTMENT_ID")
    private String staffDepartmentId;

    @Column(name = "STAFF_RANK_CODE")
    private String staffRankCode;

    @Column(name = "STAFF_POSITION_CODE")
    private String staffPositionCode;

    @Column(name = "STAFF_PHONE")
    private String staffPhone;

    @Column(name = "STAFF_EXTENSION_NO")
    private String staffExtensionNo;

    @Column(name = "STAFF_EMAIL")
    private String staffEmail;

    @Column(name = "STAFF_HIRE_DATE")
    private LocalDate staffHireDate;

    @Column(name = "STAFF_STATUS")
    private String staffStatus;

    @Column(name = "STAFF_BIRTH_DATE")
    private LocalDate staffBirthDate;

    @Column(name = "STAFF_LICENSE_NO")
    private String staffLicenseNo;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
