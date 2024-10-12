package ru.skypro.Lists.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.exception.EmployeeAlreadyAddedException;
import ru.skypro.Lists.exception.EmployeeNotFoundException;
import ru.skypro.Lists.exception.EmployeeStorageIsFullException;
import ru.skypro.Lists.service.ListService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    public static final int MAX_EMPLOYEES = 100;

    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov"),
            new Employee("Petr", "Petrov"),
            new Employee("Sidor", "Sidorov")));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> list() {
        return Collections.unmodifiableList(employees);
    }


}
