package com.example.streamapioptional.service;

import com.example.streamapioptional.exception.EmployeeAlreadyAddedException;
import com.example.streamapioptional.exception.EmployeeNotFoundException;
import com.example.streamapioptional.exception.EmployeeStorageIsFullException;
import com.example.streamapioptional.start.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String,Employee> employees = new HashMap(MAXSIZE);
    private static final int MAXSIZE = 5;

    public EmployeeService() {
        Employee employee1 = new Employee("ivan", "Vin", 1, 10000);
        Employee employee2 = new Employee("oleg", "win", 1, 30000);
        Employee employee3 = new Employee("cup", "min", 2, 15000);
        Employee employee4 = new Employee("sim", "sim", 2, 45000);
        employees.put(createKey(employee1), employee1);
        employees.put(createKey(employee2), employee2);
        employees.put(createKey(employee3), employee3);
        employees.put(createKey(employee4), employee4);
    }

    public Employee add(String fistName, String lastName) {
        if (employees.size() >= MAXSIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeAdd = new Employee(fistName, lastName);
        if (employees.containsKey(createKey(fistName,lastName,))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(fistName, lastName), employeeAdd);
        return employeeAdd;
    } // добавим сотрудника

    public Employee remove(String firstName,String lastName) {
        if (!employees.containsKey(createKey(firstName,lastName))) {
            throw new EmployeeNotFoundException();
        };
        return employees.remove(createKey(firstName,lastName));
    } // Удалим сотрудника

    public Employee find(String firstName, String lastName) {
        if (!employees.containsKey(createKey(firstName,lastName))){
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }
    public List<Employee> getall(){
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));

    }
    private String createKey(String firstName, String lastName){
        return (firstName + lastName).toLowerCase();

    }
}