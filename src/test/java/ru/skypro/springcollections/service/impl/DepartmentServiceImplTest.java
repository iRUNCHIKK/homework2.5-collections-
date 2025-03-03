package ru.skypro.springcollections.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.EmployeeService;
import ru.skypro.springcollections.service.Impl.DepartmentServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    private final Map<String , Employee> employees = new HashMap<>() {{
        put("DianaStarostina", new Employee("Diana", "Starostina", 100_100, 2));
        put("SashaSankov", new Employee("Sasha", "Sankov", 155_800, 2));
        put("IraKylikova", new Employee("Ira", "Kylikova", 189_000, 2));
    }};

    @Test
    @DisplayName("Корректно считает сумму зарплат")
    void salarySum() {
        int departmentId = 2;
        int expectedSum = 444_900;

        Mockito.when(employeeService.getAll()).thenReturn(employees);
        //test
        int actualSum = departmentServiceImpl.getEmployeeSalarySum(departmentId);
        //check
        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    @DisplayName("Корректно находит сотрудника с максимальной зарплатой")
    void maSalary() {
        int departmentId = 2;
        Employee expectedEmployee = employees.get("IraKylikova");

        Mockito.when(employeeService.getAll()).thenReturn(employees);
        //test
        Employee actualEmployee = departmentServiceImpl.getEmployeeWithMaxSalary(departmentId);
        //check
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    @DisplayName("Корректно находит сотрудника с минимальной зарплатой")
    void minSalary() {
        int departmentId = 2;
        Employee expectedEmployee = employees.get("DianaStarostina");

        Mockito.when(employeeService.getAll()).thenReturn(employees);
        //test
        Employee actualEmployee = departmentServiceImpl.getEmployeeWithMinSalary(departmentId);
        //check
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    @DisplayName("Корректно находит сотрудников с одного отдела")
    void all() {
        int departmentId = 2;
        Collection<Employee> expectedEmployees = new ArrayList<>(employees.values());

        Mockito.when(employeeService.getAll()).thenReturn(employees);
        //test
        List<Employee> actualEmployees = departmentServiceImpl.getAllEmployeesByDepartmentId(departmentId);
        //check
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    @DisplayName("Корректно находит разгруппированных по отделам сотрудников")
    void grouped() {
        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>() {{
            put(2, new ArrayList<>(employees.values()));
        }};

        Mockito.when(employeeService.getAll()).thenReturn(employees);
        //test
        Map<Integer, List<Employee>> actualEmployees = departmentServiceImpl.getAllEmployeesGroupedByDepartmentId();
        //check
        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

}
