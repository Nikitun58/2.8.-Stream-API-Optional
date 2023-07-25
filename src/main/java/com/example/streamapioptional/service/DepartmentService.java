package com.example.streamapioptional.service;

import com.example.streamapioptional.exception.EmployeeNotFoundException;
import com.example.streamapioptional.start.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        return employeeService.getall().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.getall().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll(int department) {
        return employeeService.getall().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getall().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
