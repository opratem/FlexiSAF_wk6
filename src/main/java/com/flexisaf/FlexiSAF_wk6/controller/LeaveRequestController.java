package com.flexisaf.FlexiSAF_wk6.controller;


import com.flexisaf.FlexiSAF_wk6.entity.LeaveRequest;
import com.flexisaf.FlexiSAF_wk6.repository.LeaveRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave_requests")
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestController (LeaveRequestRepository leaveRequestRepository){
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @PostMapping
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }
}
