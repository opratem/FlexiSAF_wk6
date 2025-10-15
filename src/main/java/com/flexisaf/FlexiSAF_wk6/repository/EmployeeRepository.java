package com.flexisaf.FlexiSAF_wk6.repository;

import com.flexisaf.FlexiSAF_wk6.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
}
