package ru.skypro.springcollections.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.skypro.springcollections.exceptions.EmployeeAlreadyAddedException;
import ru.skypro.springcollections.exceptions.EmployeeNotFoundException;
import ru.skypro.springcollections.exceptions.EmployeeStorageIsFullException;
import ru.skypro.springcollections.model.Employee;
import ru.skypro.springcollections.service.Impl.EmployeeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static ru.skypro.springcollections.service.Impl.EmployeeServiceImpl.STORAGE_SIZE;

public class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeServiceImpl;

    @BeforeEach
    public void setUp() {
        employeeServiceImpl = new EmployeeServiceImpl();
    }

    @Test
    @DisplayName("Корректно добавляет нового сотрудника")
    void add1() {
        Employee expected = new Employee("Albert", "Lelhov", 135_000, 2);
        //test
        Employee actual = employeeServiceImpl.add(expected.getFirstName(), expected.getLastName(), expected.getSalary(), expected.getDepartment());
        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Не добавляет сотрудников в полное хранилище")
    void add2() {
        for (int i = 0; i < STORAGE_SIZE; i++) {
            employeeServiceImpl.add("Egor", "Ivanov" + i, 1000_000, 2);
        }

        Assertions.assertThrows(
                EmployeeStorageIsFullException.class,
                () -> {
                    employeeServiceImpl.add("Egor", "litvenkov", 110_000, 3);
                }
        );
    }

    @Test
    @DisplayName("Не допускает повтора сотрудников в хранилище")
    void add3() {
        Employee expected = new Employee("Ivan", "Lelhov", 140_500, 1);
        //test
        Employee actual = employeeServiceImpl.add(expected.getFirstName(), expected.getLastName(), expected.getSalary(), expected.getDepartment());
        //check
        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> {
                    employeeServiceImpl.add("Ivan", "Lelhov", 110_000, 3);
                }
        );
    }

    @Test
    @DisplayName("Корректно удаляет сотрудника")
    void remove1() {
        Employee employee = new Employee("Nikita", "Ivanchenko", 90_000, 1);
        employeeServiceImpl.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        //test
        Employee removedEmployee = employeeServiceImpl.remove(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        //check
        Assertions.assertEquals(employee, removedEmployee);
    }

    @Test
    @DisplayName("Выдает ошибку при попытке удалить не существующего в хранилище сотрудника")
    void remove2() {
        Employee expected = new Employee("Peter", "Pavlov", 111_900, 2);
        //check
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeServiceImpl.remove("Peter", "Pavlov", 111_900, 2)
        );
    }

    @Test
    @DisplayName("Корректно находит сотрудника")
    void find1() {
        Employee expected = employeeServiceImpl.add("Alina", "Timofeeva", 98_390, 3);
        //test
        Employee actual = employeeServiceImpl.find("Alina", "Timofeeva", 98_390, 3);
        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Выдает ошибку, если сотрудник не найден в хранилище")
    void find2() {
        //check
        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeServiceImpl.find("Lera", "Petrova", 85_000, 3)
        );
    }

}