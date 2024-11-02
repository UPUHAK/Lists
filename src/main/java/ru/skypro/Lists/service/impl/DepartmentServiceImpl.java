package ru.skypro.Lists.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.service.DepartmentService;
import ru.skypro.Lists.service.ListService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final ListService listService;

    public DepartmentServiceImpl(ListService listService) {
        this.listService = listService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int department) {
        return listService.list()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(int department) {
        return listService.list()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentID(int department) {
        return listService.list()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentID() {
        return listService.list()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
