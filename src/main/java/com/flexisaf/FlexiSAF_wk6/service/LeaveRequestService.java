package com.flexisaf.FlexiSAF_wk6.service;

import com.flexisaf.FlexiSAF_wk6.entity.LeaveRequest;
import com.flexisaf.FlexiSAF_wk6.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    // Constructor Injection
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    //Get all leave requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    //Get leave request by ID
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    //Create a leave request
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    //Update Leave Request
    @Transactional
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest updatedLeaveRequest) {
        return leaveRequestRepository.findById(id)
                .map(leaveRequest -> {
                    leaveRequest.setStartDate(updatedLeaveRequest.getStartDate());
                    leaveRequest.setEndDate(updatedLeaveRequest.getEndDate());
                    leaveRequest.setLeaveType(updatedLeaveRequest.getLeaveType());
                    leaveRequest.setStatus(updatedLeaveRequest.getStatus());
                    leaveRequest.setReason(updatedLeaveRequest.getReason());
                    leaveRequest.setManagerComment(updatedLeaveRequest.getManagerComment());
                    leaveRequest.setDateReviewed(updatedLeaveRequest.getDateReviewed());

                    leaveRequest.setEmployee(updatedLeaveRequest.getEmployee());
                    return  leaveRequestRepository.save(leaveRequest);
                })
                .orElseThrow(() -> new RuntimeException("Leave request with ID " +id + "not found"));
    }

    //Get leave requests by employee
    public List<LeaveRequest> getLeaveRequestByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployee_Id(employeeId);
    }

    //Delete leave request
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
}
