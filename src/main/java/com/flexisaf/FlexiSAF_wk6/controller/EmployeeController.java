package com.flexisaf.FlexiSAF_wk6.controller;

import com.flexisaf.FlexiSAF_wk6.entity.Employee;
import com.flexisaf.FlexiSAF_wk6.repository.EmployeeRepository;
import com.flexisaf.FlexiSAF_wk6.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){

        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {
        try {
            Employee savedEmployee = employeeService.updateEmployee(id, updatedEmployee);
            return ResponseEntity.ok(savedEmployee);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updatePartialEmployee(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            Employee updatedEmployee = employeeService.patchEmployee(id, updates);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
