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
        //given
        int departmentID = 2;
        int expectedSum = 60;

        Mockito.when(listService.list()).thenReturn(employees.values());

        //when
        int actualSum = departmentService.getSalarySum(departmentID);

        //then
        Assertions.assertEquals(expectedSum, actualSum);

    }

    @Test
    void shouldCorrectlyFindEmployeeWithMaxSalary() {
        //given
        int departmentID = 2;
        Employee expectedEmployee = employees.get("Ivan3Ivanov");

        Mockito.when(listService.list()).thenReturn(employees.values());

        //when
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentID);

        //then
        Assertions.assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void shouldCorrectlyFindEmployeeWithMinSalary() {
        //given
        int departmentID = 2;
        Employee expectedEmployee = employees.get("Ivan1Ivanov");

        Mockito.when(listService.list()).thenReturn(employees.values());

        //when
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentID);

        //then
        Assertions.assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void shouldCorrectlyFindEmployeesByDepartmentID() {
        //given
        int departmentID = 2;
        Collection<Employee> expectedEmployees = new ArrayList<>(employees.values());

        Mockito.when(listService.list()).thenReturn(employees.values());

        //when
        Collection<Employee> actualEmployees = departmentService.getAllEmployeesByDepartmentID(departmentID);

        //then
        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }

    @Test
    void shouldCorrectlyGroupedEmployeesByDepartmentID() {
        //given
        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>() {{
            put(2, new ArrayList<>(employees.values()));
        }};

        Mockito.when(listService.list()).thenReturn(employees.values());

        //when
        Map<Integer, List<Employee>> actualEmployees = departmentService.getAllEmployeesGroupedByDepartmentID();

        //then
        Assertions.assertEquals(expectedEmployees, actualEmployees);

    }
}