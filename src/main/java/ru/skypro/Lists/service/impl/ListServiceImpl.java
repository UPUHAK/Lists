package ru.skypro.Lists.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.exception.EmployeeAlreadyAddedException;
import ru.skypro.Lists.exception.EmployeeNotFoundException;
import ru.skypro.Lists.exception.EmployeeStorageIsFullException;
import ru.skypro.Lists.service.ListService;

import java.util.*;

@Service
public class ListServiceImpl implements ListService {

    public static final int MAX_EMPLOYEES = 100;

    Map<String, Employee> employees = new HashMap<>();

    public String key(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(key(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key(employee), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.containsKey(key(employee))) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(key(employee));
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.containsKey(key(employee))) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> list() {
        return Collections.unmodifiableMap(employees).values();
    }


}
