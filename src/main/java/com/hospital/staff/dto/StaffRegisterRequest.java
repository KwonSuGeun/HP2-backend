package com.hospital.staff.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffRegisterRequest {

    private String staffId;
    private String staffPassword;
    private String staffName;
    private String staffType;
    private String staffRoleCode;
    private String staffDepartmentId;
    private String staffRankCode;
    private String staffPositionCode;
    private String staffPhone;
    private String staffExtensionNo;
    private String staffEmail;
    private LocalDate staffHireDate;
    private LocalDate staffBirthDate;
    private String staffLicenseNo;
}
