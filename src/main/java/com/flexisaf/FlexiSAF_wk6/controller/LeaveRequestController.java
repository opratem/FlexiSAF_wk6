package com.flexisaf.FlexiSAF_wk6.controller;


import com.flexisaf.FlexiSAF_wk6.entity.LeaveRequest;
import com.flexisaf.FlexiSAF_wk6.repository.LeaveRequestRepository;
import com.flexisaf.FlexiSAF_wk6.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave_requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequest(@PathVariable Long id) {
        return leaveRequestService.getLeaveRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId")
    public List<LeaveRequest> getByEmployee(@PathVariable Long employeeId) {
        return leaveRequestService.getLeaveRequestByEmployee(employeeId);
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest);
        return ResponseEntity.ok(createdLeaveRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(
            @PathVariable Long id,
            @RequestBody LeaveRequest updatedLeaveRequest) {
        try {
            LeaveRequest savedLeaveRequest = leaveRequestService.updateLeaveRequest(id, updatedLeaveRequest);
            return ResponseEntity.ok(savedLeaveRequest);
        } catch (RuntimeException ex) {
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.notFound().build();
    }
}