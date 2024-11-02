package ru.skypro.Lists.service;

import ru.skypro.Lists.Employee;

import java.util.Collection;

public interface ListService {

    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName, int salary, int department);

    Employee findEmployee(String firstName, String lastName, int salary, int department);

    Collection<Employee> list();

}
