package com.hospital.service;

import com.hospital.dto.StaffDto;
import com.hospital.dto.StaffListRequest;

import java.util.List;

public interface StaffService {

    List<StaffDto> getStaffList(StaffListRequest request);

    StaffDto getStaffById(String staffId);
}
