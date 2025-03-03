package ru.skypro.springcollections.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}/employees")
    public List<Employee> getAllEmployeesByDepartmentId(@PathVariable int departmentId) {
        return departmentService.getAllEmployeesByDepartmentId(departmentId);
    }

    @GetMapping("{departmentId}/salary/sum")
    public int getEmployeeSalarySum(@PathVariable int departmentId) {
        return departmentService.getEmployeeSalarySum(departmentId);
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
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentId() {
        return departmentService.getAllEmployeesGroupedByDepartmentId();
    }
}
