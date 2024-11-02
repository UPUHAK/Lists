package ru.skypro.Lists.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.Lists.Employee;
import ru.skypro.Lists.service.ListService;

import java.util.Collection;


@RestController
@RequestMapping("employee")
public class ListsController {

    private final ListService listService;

    public ListsController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    public String greeting() {
        return "Отдел кадров";
    }

    @GetMapping("getAll")
    public Collection<Employee> list() {
        return listService.list();
    }

    @GetMapping("add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, int salary, int department) {
        return listService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, int salary, int department) {
        return listService.removeEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, int salary, int department) {
        return listService.findEmployee(firstName, lastName, salary, department);
    }


}
