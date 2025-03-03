package ru.skypro.springcollections.service;

import ru.skypro.springcollections.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    int getEmployeeSalarySum (int departmentId);

    Employee getEmployeeWithMaxSalary (int departmentId);

    Employee getEmployeeWithMinSalary (int departmentId);

    List<Employee> getAllEmployeesByDepartmentId (int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartmentId();
}
