package com.hospital.staff.repository;

import com.hospital.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {

    // JOIN FETCH: 부서 필터(d.departmentId) + DTO 부서명 + N+1 방지
    @Query("SELECT s FROM Staff s " +
           "LEFT JOIN FETCH s.department d " +
           "WHERE (:dept IS NULL OR d.departmentId = :dept) " +
           "AND (:keyword IS NULL OR s.staffName LIKE CONCAT('%', :keyword, '%') " +
           "OR s.staffId LIKE CONCAT('%', :keyword, '%')) " +
           "ORDER BY s.staffId ASC")
    List<Staff> searchStaffList(@Param("dept") String dept,
                                @Param("keyword") String keyword);

    Optional<Staff> findByStaffIdAndStaffStatus(String staffId, String staffStatus);

    boolean existsByStaffEmail(String staffEmail);
}
