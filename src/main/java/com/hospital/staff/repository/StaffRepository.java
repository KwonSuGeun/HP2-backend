package com.hospital.staff.repository;

import com.hospital.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, String> {

    @Query("SELECT s, d.departmentName FROM Staff s " +
           "LEFT JOIN Department d ON s.staffDepartmentId = d.departmentId " +
           "WHERE (:staffId IS NULL OR s.staffId = :staffId) " +
           "AND (:dept IS NULL OR s.staffDepartmentId = :dept) " +
           "AND (:staffRankCode IS NULL OR s.staffRankCode = :staffRankCode) " +
           "AND (:keyword IS NULL OR s.staffName LIKE %:keyword% OR s.staffId LIKE %:keyword%) " +
           "ORDER BY s.staffId")
    List<Object[]> searchStaffList(@Param("staffId") String staffId,
                                   @Param("dept") String dept,
                                   @Param("staffRankCode") String staffRankCode,
                                   @Param("keyword") String keyword);

    Optional<Staff> findByStaffIdAndStaffStatus(String staffId, String staffStatus);

    boolean existsByStaffEmail(String staffEmail);
}
