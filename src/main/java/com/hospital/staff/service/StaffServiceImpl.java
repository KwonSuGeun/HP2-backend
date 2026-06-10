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
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private static final String STAFF_STATUS_ACTIVE = "재직";

    private static final Set<String> ALLOWED_STAFF_TYPES = Set.of("DOC", "NUR", "ADM");

    private static final Map<String, String> ROLE_CODE_BY_TYPE = Map.of(
            "DOC", "ROLE_DOCTOR",
            "NUR", "ROLE_NURSE",
            "ADM", "ROLE_ADMIN"
    );

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<StaffDto> searchStaffList(StaffListRequest request) {
        return staffRepository.searchStaffList(
                        nullToEmpty(request.getStaffId()),
                        nullToEmpty(request.getDept()),
                        nullToEmpty(request.getStaffRankCode()),
                        nullToEmpty(request.getKeyword()))
                .stream()
                .map(row -> toStaffDto((Staff) row[0], (String) row[1]))
                .toList();
    }

    @Override
    public StaffDto getStaffById(String staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new BusinessException(ErrorCode.STAFF_NOT_FOUND));

        String departmentName = departmentRepository.findById(staff.getStaffDepartmentId())
                .map(Department::getDepartmentName)
                .orElse(null);

        return toStaffDto(staff, departmentName);
    }

    @Override
    @Transactional // save or delete or update 가 필요한 메서드 일때 만 사용 읽기전용 메서드에는 붙이지 않는게 좋음
    public void registerStaff(StaffRegisterRequest request) {
        validateRegisterRequest(request);

        if (staffRepository.existsById(request.getStaffId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_STAFF_ID);
        }

        if (!departmentRepository.existsById(request.getStaffDepartmentId())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        Staff staff = toStaffEntity(request);
        staffRepository.save(staff);
    }

    @Override
    public List<DepartmentDto> getDepartmentList() {
        return departmentRepository.findByIsActiveOrderByDepartmentId("Y")
                .stream()
                .map(this::toDepartmentDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteStaff(String staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new BusinessException(ErrorCode.STAFF_NOT_FOUND);
        }
        staffRepository.deleteById(staffId);
    }

    private void validateRegisterRequest(StaffRegisterRequest request) {
        if (!StringUtils.hasText(request.getStaffId())
                || !StringUtils.hasText(request.getStaffPassword())
                || !StringUtils.hasText(request.getStaffName())
                || !StringUtils.hasText(request.getStaffType())
                || !StringUtils.hasText(request.getStaffDepartmentId())
                || !StringUtils.hasText(request.getStaffRankCode())
                || !StringUtils.hasText(request.getStaffPhone())
                || !StringUtils.hasText(request.getAddressZipCode())
                || !StringUtils.hasText(request.getAddressBase())
                || request.getStaffHireDate() == null
                || request.getStaffBirthDate() == null) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        if (!ALLOWED_STAFF_TYPES.contains(request.getStaffType())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        if (requiresLicense(request.getStaffType()) && !StringUtils.hasText(request.getStaffLicenseNo())) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }
    }

    private Staff toStaffEntity(StaffRegisterRequest request) {
        Staff staff = new Staff();
        staff.setStaffId(request.getStaffId().trim());
        staff.setStaffPassword(request.getStaffPassword());
        staff.setStaffName(request.getStaffName().trim());
        staff.setStaffType(request.getStaffType());
        staff.setStaffRoleCode(resolveRoleCode(request));
        staff.setStaffDepartmentId(request.getStaffDepartmentId());
        staff.setStaffRankCode(request.getStaffRankCode());
        staff.setStaffPositionCode(request.getStaffPositionCode());
        staff.setStaffPhone(request.getStaffPhone().trim());
        staff.setStaffExtensionNo(request.getStaffExtensionNo());
        staff.setStaffEmail(request.getStaffEmail());
        staff.setStaffHireDate(request.getStaffHireDate());
        staff.setStaffBirthDate(request.getStaffBirthDate());
        staff.setStaffLicenseNo(request.getStaffLicenseNo());
        staff.setStaffAddress(buildFullAddress(request));
        staff.setStaffStatus(STAFF_STATUS_ACTIVE);
        return staff;
    }

    private boolean requiresLicense(String staffType) {
        return "DOC".equals(staffType) || "NUR".equals(staffType);
    }

    private String resolveRoleCode(StaffRegisterRequest request) {
        if (StringUtils.hasText(request.getStaffRoleCode())) {
            return request.getStaffRoleCode();
        }
        return ROLE_CODE_BY_TYPE.get(request.getStaffType());
    }

    private String buildFullAddress(StaffRegisterRequest request) {
        StringBuilder address = new StringBuilder();
        address.append("[").append(request.getAddressZipCode().trim()).append("] ");
        address.append(request.getAddressBase().trim());
        if (StringUtils.hasText(request.getAddressDetail())) {
            address.append(" ").append(request.getAddressDetail().trim());
        }
        return address.toString().trim();
    }

    private String nullToEmpty(String value) {
        return StringUtils.hasText(value) ? value : null;
    }

    private StaffDto toStaffDto(Staff staff, String departmentName) {
        StaffDto dto = new StaffDto();
        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setStaffType(staff.getStaffType());
        dto.setStaffRoleCode(staff.getStaffRoleCode());
        dto.setStaffDepartmentId(staff.getStaffDepartmentId());
        dto.setStaffDepartmentName(departmentName);
        dto.setStaffRankCode(staff.getStaffRankCode());
        dto.setStaffPositionCode(staff.getStaffPositionCode());
        dto.setStaffPhone(staff.getStaffPhone());
        dto.setStaffExtensionNo(staff.getStaffExtensionNo());
        dto.setStaffEmail(staff.getStaffEmail());
        dto.setStaffHireDate(staff.getStaffHireDate());
        dto.setStaffStatus(staff.getStaffStatus());
        dto.setStaffBirthDate(staff.getStaffBirthDate());
        dto.setStaffLicenseNo(staff.getStaffLicenseNo());
        dto.setStaffAddress(staff.getStaffAddress());
        return dto;
    }

    private DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        return dto;
    }
}
