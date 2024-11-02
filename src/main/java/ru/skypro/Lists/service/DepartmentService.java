package ru.skypro.Lists.service;

import ru.skypro.Lists.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(int department);

    Employee getEmployeeWithMinSalary(int department);

    List<Employee> getAllEmployeesByDepartmentID(int department);

    Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentID();

}
