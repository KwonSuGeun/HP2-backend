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
    /** 직군: DOC(의사), NUR(간호), ADM(행정) */
    private String staffType;
    /** 미입력 시 staffType으로 자동 매핑 */
    private String staffRoleCode;
    /** 부서 ID (예: DEPT001) — DEPARTMENT_NAME 아님 */
    private String staffDepartmentId;
    private String staffRankCode;
    private String staffPositionCode;
    private String staffPhone;
    /** 등록 시 부서(STAFF_DEPARTMENT) 내선번호로 자동 설정 — 요청값 무시 */
    private String staffExtensionNo;
    private String staffEmail;
    private LocalDate staffHireDate;
    private LocalDate staffBirthDate;
    /** 의사/간호사(DOC, NUR)일 때 필수 */
    private String staffLicenseNo;
    /** 우편번호 */
    private String addressZipCode;
    /** 기본주소 */
    private String addressBase;
    /** 상세주소 */
    private String addressDetail;
}
