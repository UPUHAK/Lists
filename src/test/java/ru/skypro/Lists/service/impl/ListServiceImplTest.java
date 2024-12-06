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
        Employee employeeToAdd = new Employee("John", "Jonathan", 10000, 1);

        Employee addedEmployee = listService.addEmployee(
                employeeToAdd.getFirstName(),
                employeeToAdd.getLastName(),
                employeeToAdd.getSalary(),
                employeeToAdd.getDepartment()
        );

        Assertions.assertEquals(employeeToAdd, addedEmployee);
    }

    @Test
    public void shouldThrowEmployeeStorageIsFullExceptionWhenMaxEmployeesExceeded() {
        for (int i = 0; i < MAX_EMPLOYEES; i++) {
            listService.addEmployee("John" + i, "Jonathan", 10000, 1);
        }


        Assertions.assertThrows(
                EmployeeStorageIsFullException.class,
                () -> listService.addEmployee("Test", "Testson", 5000, 2)
        );
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeAlreadyAdded() {
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());


        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment())
        );
    }


    @Test
    public void shouldCorrectlyRemoveEmployee() {
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Employee removedEmployee = listService.removeEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertEquals(employee, removedEmployee);

    }

    @Test
    public void shouldEmployeeNotFoundExceptionWhenEmployeeToRemoveNotFound() {
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> listService.removeEmployee("John", "Jonathan", 10000, 1)
        );
    }

    @Test
    public void shouldCorrectlyFindEmployee() {
        Employee employee = new Employee("John", "Jonathan", 10000, 1);
        listService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Employee findedEmployee = listService.findEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());

        Assertions.assertEquals(employee, findedEmployee);

    }

    @Test
    public void shouldEmployeeNotFoundExceptionWhenEmployeeToFindNotFound() {
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> listService.findEmployee("John", "Jonathan", 10000, 1)
        );
    }

}