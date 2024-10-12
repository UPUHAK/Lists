package ru.skypro.Lists.service;

import ru.skypro.Lists.Employee;

import java.util.Collection;

public interface ListService {

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> list();

}
