package ru.skypro.springcollections.service.Impl;

import jakarta.annotation.PostConstruct;
import ru.skypro.springcollections.exceptions.EmployeeAlreadyAddedException;
import ru.skypro.springcollections.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.springcollections.exceptions.EmployeeStorageIsFullException;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int STORAGE_SIZE = 11;

    private final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void initEmployees() {
        add("Egor", "Ivanov", 75_000,1);
        add("Oleg", "Efimov", 100_000,1);
        add("Maria", "Kim", 80_000,1);
        add("Pavel", "Gilbert", 80_000,3);
        add("Egor", "Pavlov", 75_000,3);
        add("Maria", "Ivanova", 65_000,2);
        add("Irina", "Kireeva", 99_900,3);
        add("Anna", "Selyanina", 38_000,3);
        add("Anna", "Pavlenko", 67_000,2);
        add("Albert", "Ivanov", 83_000,3);
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        if (employees.size() >= STORAGE_SIZE){
            throw new EmployeeStorageIsFullException("В хранилище недостаточно места для нового сотрудника");
        }

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + "уже находится в хранилище");
        }

        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName, int salary, int departmentId) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден в хранилище");
        }

        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName, int salary, int departmentId) {
        Employee employee = employees.get(getKey(firstName,lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден в хранилище");
        }

        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return null;
    }

    @Override
    public String getKey(String firstName, String lastName) {
        return null;
    }

    @Override
    public String getKey(Employee employee) {
        return null;
    }
}
