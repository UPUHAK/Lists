package ru.skypro.Lists.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.Lists.Employee;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private ListServiceImpl listService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> employees = new HashMap<>() {{
        put("Ivan1Ivanov", new Employee("Ivan1", "Ivanov", 10, 2));
        put("Ivan2Ivanov", new Employee("Ivan2", "Ivanov", 20, 2));
        put("Ivan3Ivanov", new Employee("Ivan3", "Ivanov", 30, 2));
    }};

    @Test
    void shouldCorrectlyCalculateSalarySum() {
        int departmentID = 2;
        int expectedSum = 60;
        Mockito.when(listService.list()).thenReturn(employees.values());

        int actualSum = departmentService.getSalarySum(departmentID);

        Assertions.assertEquals(expectedSum, actualSum);

    }

    @Test
    void shouldCorrectlyFindEmployeeWithMaxSalary() {
        int departmentID = 2;
        Employee expectedEmployee = employees.get("Ivan3Ivanov");
        Mockito.when(listService.list()).thenReturn(employees.values());

        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentID);

        Assertions.assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void shouldCorrectlyFindEmployeeWithMinSalary() {
        int departmentID = 2;
        Employee expectedEmployee = employees.get("Ivan1Ivanov");
        Mockito.when(listService.list()).thenReturn(employees.values());

        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentID);

        Assertions.assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void shouldCorrectlyFindEmployeesByDepartmentID() {
        int departmentID = 2;
        Collection<Employee> expectedEmployees = new ArrayList<>(employees.values());
        Mockito.when(listService.list()).thenReturn(employees.values());

        Collection<Employee> actualEmployees = departmentService.getAllEmployeesByDepartmentID(departmentID);

        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }

    @Test
    void shouldCorrectlyGroupedEmployeesByDepartmentID() {
        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>() {{
            put(2, new ArrayList<>(employees.values()));
        }};
        Mockito.when(listService.list()).thenReturn(employees.values());

        Map<Integer, List<Employee>> actualEmployees = departmentService.getAllEmployeesGroupedByDepartmentID();

        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }
}