package com.hospital.staff.repository;

import com.hospital.staff.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    List<Department> findByIsActiveOrderByDepartmentId(String isActive);
}
