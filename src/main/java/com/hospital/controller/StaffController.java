package com.hospital.controller;

import com.hospital.common.ApiResponse;
import com.hospital.dto.StaffDto;
import com.hospital.dto.StaffListRequest;
import com.hospital.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/account-management")
@CrossOrigin(originPatterns = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ApiResponse<List<StaffDto>> getStaffList(@RequestBody StaffListRequest request) {
        return ApiResponse.success(staffService.getStaffList(request));
    }

    @GetMapping("/{staffId}")
    public ApiResponse<StaffDto> getStaffById(@PathVariable String staffId) {
        return ApiResponse.success(staffService.getStaffById(staffId));
    }
}
