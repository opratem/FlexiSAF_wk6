package com.flexisaf.FlexiSAF_wk6.service;

import com.flexisaf.FlexiSAF_wk6.entity.Employee;
import com.flexisaf.FlexiSAF_wk6.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    //Constructor Injection
    public EmployeeService(EmployeeRepository employeeRepository) {
        
        this.employeeRepository = employeeRepository;
    }

    //Get all employees
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // Get one employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    //Create new employee
    public Employee createEmployee (Employee employee){
        return employeeRepository.save(employee);
    }

    //Update existing employee
    @Transactional
    public Employee updateEmployee (Long id, Employee updatedEmployee){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setPosition(updatedEmployee.getPosition());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setStatus(updatedEmployee.getStatus());
                    employee.setActive(updatedEmployee.isActive());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + "not found"));
    }

    //Partial Update
    @Transactional
    public Employee patchEmployee (Long id, Map<String, Object> updates){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with ID " + id + "not found"));

        updates.forEach( (key, value) -> {
            Field field = ReflectionUtils.findField(Employee.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, employee, value);
            }
        });

        return employeeRepository.save(employee);
    }

    //Delete employee
    public void deleteEmployee (Long id){
        employeeRepository.deleteById(id);
    }

    //Find by email
    public Optional <Employee> findByEmail(String email){
        return Optional.ofNullable(employeeRepository.findByEmail(email));
    }
}
