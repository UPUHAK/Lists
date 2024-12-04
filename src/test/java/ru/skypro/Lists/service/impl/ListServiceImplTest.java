package ru.skypro.Lists.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.exception.EmployeeAlreadyAddedException;
import ru.skypro.Lists.exception.EmployeeNotFoundException;
import ru.skypro.Lists.exception.EmployeeStorageIsFullException;

import static ru.skypro.Lists.service.impl.ListServiceImpl.MAX_EMPLOYEES;

class ListServiceImplTest {

    private ListServiceImpl listService = new ListServiceImpl();

    @BeforeEach
    public void clear() {
        listService = new ListServiceImpl();
    }

    @Test
    public void shouldCorrectlyAddEmployee() {
        //given
        Employee employeeToAdd = new Employee("John", "Jonathan", 10000, 1);

        //when
        Employee addedEmployee = listService.addEmployee(
                employeeToAdd.getFirstName(),
                employeeToAdd.getLastName(),
                employeeToAdd.getSalary(),
                employeeToAdd.getDepartment()
        );

        //then
        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void shouldThrowEmployeeStorageIsFullExceptionWhenMaxEmployeesExceeded() {
        //given

        for (int i = 0; i < MAX_EMPLOYEES; i++) {
            listService.addEmployee("John" + i, "Jonathan", 10000, 1);
        }

        //when
        //then
        Assertions.assertThrows(
                EmployeeStorageIsFullException.class,
                () -> listService.addEmployee("Test", "Testson", 5000, 2)
        );
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeAlreadyAdded() {
        //given
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        //when

        //then
        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment())
        );
    }


    @Test
    public void shouldCorrectlyRemoveEmployee() {
        //given
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        //when
        Employee removedEmployee = listService.removeEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        //then
        Assertions.assertEquals(employee, removedEmployee);

    }

    @Test
    public void shouldEmployeeNotFoundExceptionWhenEmployeeToRemoveNotFound() {
        //given
        //when
        //then
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> listService.removeEmployee("John", "Jonathan", 10000, 1)
        );
    }

    @Test
    public void shouldCorrectlyFindEmployee() {
        //given
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        //when
        Employee findedEmployee = listService.findEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        //then
        Assertions.assertEquals(employee, findedEmployee);

    }

    @Test
    public void shouldEmployeeNotFoundExceptionWhenEmployeeToFindNotFound() {
        //given
        //when
        //then
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> listService.findEmployee("John", "Jonathan", 10000, 1)
        );
    }

}