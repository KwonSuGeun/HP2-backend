package com.hospital.staff.controller;

import com.hospital.common.ApiResponse;
import com.hospital.staff.dto.DepartmentDto;
import com.hospital.staff.dto.StaffDto;
import com.hospital.staff.dto.StaffListRequest;
import com.hospital.staff.dto.StaffRegisterRequest;
import com.hospital.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

/**
 * 직원 관리 API.
 * <ul>
 *   <li>조회: 목록 검색, 단건, 부서 마스터</li>
 *   <li>등록: 신규 직원 INSERT</li>
 * </ul>
 */
@RestController
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    /** 직원 목록 조회 — 검색 조건은 RequestBody */
    @PostMapping("/search")
    public ApiResponse<List<StaffDto>> searchStaffList(@RequestBody StaffListRequest request) {
        return ApiResponse.success(staffService.searchStaffList(request));
    }

    /** 부서 목록 조회 (등록 폼 드롭다운) — {@code /{staffId}} 보다 위에 둠 */
    @GetMapping("/departments")
    public ApiResponse<List<DepartmentDto>> getDepartmentList() {
        return ApiResponse.success(staffService.getDepartmentList());
    }

    /** 직원 단건 조회 */
    @GetMapping("/{staffId}")
    public ApiResponse<StaffDto> getStaffById(@PathVariable String staffId) {
        return ApiResponse.success(staffService.getStaffById(staffId));
    }

    /** 직원 삭제 */
    @DeleteMapping("/{staffId}")
    public ApiResponse<Void> deleteStaff(@PathVariable String staffId) {
        staffService.deleteStaff(staffId);
        return ApiResponse.success(null);
    }

    /** 신규 직원 등록 */
    @PostMapping("/register")
    public ApiResponse<Void> registerStaff(@RequestBody StaffRegisterRequest request) {
        staffService.registerStaff(request);
        return ApiResponse.success(null);
    }
}
