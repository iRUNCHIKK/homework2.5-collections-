package ru.skypro.springcollections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("all")
    public List<Employee> getAllEmployeesByDepartmentId(@RequestParam int departmentId) {
        return departmentService.getAllEmployeesByDepartmentId(departmentId);
    }

    @GetMapping("all-grouped")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentId() {
        return departmentService.getAllEmployeesGroupedByDepartmentId();
    }
}
