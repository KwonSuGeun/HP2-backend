package com.hospital.staff.controller;

import com.hospital.common.ApiResponse;
import com.hospital.staff.dto.DepartmentDto;
import com.hospital.staff.dto.StaffDto;
import com.hospital.staff.dto.StaffListRequest;
import com.hospital.staff.dto.StaffRegisterRequest;
import com.hospital.staff.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/staff")
@CrossOrigin(originPatterns = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ApiResponse<List<StaffDto>> getStaffList(
            @RequestParam(required = false) String dept,
            @RequestParam(required = false) String staffRankCode,
            @RequestParam(required = false) String keyword) {
        StaffListRequest request = new StaffListRequest();
        request.setDept(dept);
        request.setStaffRankCode(staffRankCode);
        request.setKeyword(keyword);
        return ApiResponse.success(staffService.getStaffList(request));
    }

    @GetMapping("/{staffId}")
    public ApiResponse<StaffDto> getStaffById(@PathVariable String staffId) {
        return ApiResponse.success(staffService.getStaffById(staffId));
    }

    @PostMapping
    public ApiResponse<Void> registerStaff(@RequestBody StaffRegisterRequest request) {
        staffService.registerStaff(request);
        return ApiResponse.success(null);
    }

    @GetMapping("/departments")
    public ApiResponse<List<DepartmentDto>> getDepartmentList() {
        return ApiResponse.success(staffService.getDepartmentList());
    }
}
