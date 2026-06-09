package com.hospital.staff.service;

import com.hospital.staff.dto.DepartmentDto;
import com.hospital.staff.dto.StaffDto;
import com.hospital.staff.dto.StaffListRequest;
import com.hospital.staff.dto.StaffRegisterRequest;

import java.util.List;

public interface StaffService {

    List<StaffDto> searchStaffList(StaffListRequest request);

    StaffDto getStaffById(String staffId);

    void registerStaff(StaffRegisterRequest request);

    List<DepartmentDto> getDepartmentList();
}
