package com.flexisaf.FlexiSAF_wk6.repository;

import com.flexisaf.FlexiSAF_wk6.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee_Id(Long employeeId);
}
