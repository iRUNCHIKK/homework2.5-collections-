package ru.skypro.springcollections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("add")
    public Employee add(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") int salary,
            @RequestParam("departmentId") int departmentId
    ) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("remove")
    public Employee remove(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") int salary,
            @RequestParam("departmentId") int departmentId
    ) {
        return employeeService.remove(firstName, lastName, salary, departmentId);
    }

    @GetMapping("find")
    public Employee find(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") int salary,
            @RequestParam("departmentId") int departmentId
    ) {
        return employeeService.find(firstName, lastName, salary, departmentId);
    }

    @GetMapping
    public Map<String, Employee> getAll() {
        return employeeService.getAll();
    }
}

