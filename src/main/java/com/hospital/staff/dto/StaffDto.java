package com.hospital.staff.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffDto {

    private String staffId;
    private String staffName;
    private String staffType;
    private String staffRoleCode;
    private String staffDepartmentId;
    private String staffDepartmentName;
    private String staffRankCode;
    private String staffPositionCode;
    private String staffPhone;
    private String staffExtensionNo;
    private String staffEmail;
    private LocalDate staffHireDate;
    private String staffStatus;
    private LocalDate staffBirthDate;
    private String staffLicenseNo;
    private String staffAddress;
    private String staffPhotoKey;
}
