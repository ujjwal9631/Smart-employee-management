package com.example.smart_employee_management.service;

import com.example.smart_employee_management.model.Employee;
import com.example.smart_employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Create (Add Employee)
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Read (Get All Employees)
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Read (Get Employee by ID)
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Update Employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return repository.findById(id)
                .map(emp -> {
                    emp.setName(updatedEmployee.getName());
                    emp.setEmail(updatedEmployee.getEmail());
                    emp.setDepartment(updatedEmployee.getDepartment());
                    emp.setSalary(updatedEmployee.getSalary());
                    return repository.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
