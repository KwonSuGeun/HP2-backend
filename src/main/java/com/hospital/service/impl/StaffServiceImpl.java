package com.hospital.service.impl;

import com.hospital.common.ErrorCode;
import com.hospital.dto.StaffDto;
import com.hospital.dto.StaffListRequest;
import com.hospital.entity.Staff;
import com.hospital.exception.BusinessException;
import com.hospital.repository.StaffRepository;
import com.hospital.service.StaffService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<StaffDto> getStaffList(StaffListRequest request) {
        return staffRepository.findAll(buildSpecification(request)).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public StaffDto getStaffById(String staffId) {
        Staff staff = staffRepository.findByStaffIdAndStaffStatus(staffId, "ACTIVE")
                .orElseThrow(() -> new BusinessException(ErrorCode.STAFF_NOT_FOUND));
        return toDto(staff);
    }

    private Specification<Staff> buildSpecification(StaffListRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("staffStatus"), "ACTIVE"));

            if (StringUtils.hasText(request.getDept())) {
                predicates.add(cb.equal(root.get("staffDepartmentId"), request.getDept()));
            }
            if (StringUtils.hasText(request.getStaffRankCode())) {
                predicates.add(cb.equal(root.get("staffRankCode"), request.getStaffRankCode()));
            }
            if (StringUtils.hasText(request.getStatus())) {
                predicates.add(cb.equal(root.get("staffStatus"), request.getStatus()));
            }
            if (StringUtils.hasText(request.getKeyword())) {
                String keyword = "%" + request.getKeyword() + "%";
                predicates.add(cb.or(
                        cb.like(root.get("staffName"), keyword),
                        cb.like(root.get("staffId"), keyword)
                ));
            }

            query.orderBy(cb.asc(root.get("staffId")));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private StaffDto toDto(Staff staff) {
        StaffDto dto = new StaffDto();
        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setStaffType(staff.getStaffType());
        dto.setStaffRoleCode(staff.getStaffRoleCode());
        dto.setStaffDepartmentId(staff.getStaffDepartmentId());
        dto.setStaffRankCode(staff.getStaffRankCode());
        dto.setStaffPositionCode(staff.getStaffPositionCode());
        dto.setStaffPhone(staff.getStaffPhone());
        dto.setStaffExtensionNo(staff.getStaffExtensionNo());
        dto.setStaffEmail(staff.getStaffEmail());
        dto.setStaffHireDate(staff.getStaffHireDate());
        dto.setStaffStatus(staff.getStaffStatus());
        dto.setStaffBirthDate(staff.getStaffBirthDate());
        dto.setStaffLicenseNo(staff.getStaffLicenseNo());
        return dto;
    }
}
