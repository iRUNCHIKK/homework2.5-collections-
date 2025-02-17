package ru.skypro.springcollections.service;

import ru.skypro.springcollections.model.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee add(String firstName, String lastName, int salary, int departmentId);
    Employee remove(String firstName, String lastName, int salary, int departmentId);
    Employee find(String firstName, String lastName, int salary, int departmentId);

    Map<String, Employee> getAll();

    String getKey(String firstName, String lastName);

    String getKey(Employee employee);
}
