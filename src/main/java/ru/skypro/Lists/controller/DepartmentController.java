package ru.skypro.Lists.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}/employees")
    public List<Employee> getAllEmployeesByDepartmentID(@PathVariable int departmentId) {
        return departmentService.getAllEmployeesByDepartmentID(departmentId);
    }

    @GetMapping("{departmentId}/salary/sum")
    public int getSalarySum(@PathVariable int departmentId) {
        return departmentService.getSalarySum(departmentId);
    }

    @GetMapping("{departmentId}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("{departmentId}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentID() {
        return departmentService.getAllEmployeesGroupedByDepartmentID();
    }
}
