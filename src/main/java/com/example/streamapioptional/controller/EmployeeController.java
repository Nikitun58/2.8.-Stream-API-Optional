package com.example.streamapioptional.controller;

import com.example.streamapioptional.service.EmployeeService;
import com.example.streamapioptional.start.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(String fistName,String lastName, int department,double salary  ){
        return employeeService.add(new Employee(fistName, lastName,department,salary));
    }
    @GetMapping("/remove")
    public Employee remove(String fistName,String lastName, int department,double salary ){
        return employeeService.remove(fistName,lastName);
    }
    @GetMapping("find")
    public Employee find(String fistName,String lastName ){
        return employeeService.find(fistName,lastName);
    }
    @GetMapping("all")
    public List<Employee> all(){
        return employeeService.getall();

    }






}
