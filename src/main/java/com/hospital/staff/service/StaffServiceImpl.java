package com.hospital.staff.service;

import com.hospital.common.BusinessException;
import com.hospital.common.ErrorCode;
import com.hospital.staff.dto.DepartmentDto;
import com.hospital.staff.dto.StaffDto;
import com.hospital.staff.dto.StaffListRequest;
import com.hospital.staff.dto.StaffRegisterRequest;
import com.hospital.staff.entity.Department;
import com.hospital.staff.entity.Staff;
import com.hospital.staff.repository.DepartmentRepository;
import com.hospital.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<StaffDto> getStaffList(StaffListRequest request) {
        return staffRepository.searchStaffList(
                        request.getStaffId(),
                        request.getDept(),
                        request.getStaffRankCode(),
                        request.getKeyword())
                .stream()
                .map(row -> toDto((Staff) row[0], (String) row[1]))
                .toList();
    }

    @Override
    public StaffDto getStaffById(String staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new BusinessException(ErrorCode.STAFF_NOT_FOUND));
        return toDto(staff, null);
    }

    @Override
    @Transactional
    public void registerStaff(StaffRegisterRequest request) {
        if (!StringUtils.hasText(request.getStaffId())
                || !StringUtils.hasText(request.getStaffPassword())
                || !StringUtils.hasText(request.getStaffName())
                || !StringUtils.hasText(request.getStaffDepartmentId())
                || !StringUtils.hasText(request.getStaffPhone())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        if (staffRepository.existsById(request.getStaffId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_STAFF_ID);
        }

        Staff staff = new Staff();
        staff.setStaffId(request.getStaffId());
        staff.setStaffPassword(request.getStaffPassword());
        staff.setStaffName(request.getStaffName());
        staff.setStaffType(request.getStaffType());
        staff.setStaffRoleCode(request.getStaffRoleCode());
        staff.setStaffDepartmentId(request.getStaffDepartmentId());
        staff.setStaffRankCode(request.getStaffRankCode());
        staff.setStaffPositionCode(request.getStaffPositionCode());
        staff.setStaffPhone(request.getStaffPhone());
        staff.setStaffExtensionNo(request.getStaffExtensionNo());
        staff.setStaffEmail(request.getStaffEmail());
        staff.setStaffHireDate(request.getStaffHireDate());
        staff.setStaffBirthDate(request.getStaffBirthDate());
        staff.setStaffLicenseNo(request.getStaffLicenseNo());
        staff.setStaffStatus("ACTIVE");

        staffRepository.save(staff);
    }

    @Override
    public List<DepartmentDto> getDepartmentList() {
        return departmentRepository.findByIsActiveOrderByDepartmentId("Y")
                .stream()
                .map(this::toDepartmentDto)
                .toList();
    }

    private StaffDto toDto(Staff staff, String departmentName) {
        StaffDto dto = new StaffDto();
        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setStaffDepartmentId(staff.getStaffDepartmentId());
        dto.setStaffDepartmentName(departmentName);
        dto.setStaffRankCode(staff.getStaffRankCode());
        dto.setStaffPhone(staff.getStaffPhone());
        dto.setStaffStatus(staff.getStaffStatus());
        return dto;
    }

    private DepartmentDto toDepartmentDto(Department dept) {
        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentId(dept.getDepartmentId());
        dto.setDepartmentName(dept.getDepartmentName());
        return dto;
    }
}
