package com.hospital.repository;

import com.hospital.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String>, JpaSpecificationExecutor<Staff> {

    Optional<Staff> findByStaffIdAndStaffStatus(String staffId, String staffStatus);

    List<Staff> findByStaffStatus(String staffStatus);

    boolean existsByStaffEmail(String staffEmail);
}
