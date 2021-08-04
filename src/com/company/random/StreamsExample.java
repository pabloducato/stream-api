package com.company.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsExample {

    List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Honkytonk", "Cumbercooch", 25, List.of("Java", "JavaScript", "Python"));
        Employee employee2 = new Employee("Oscarbait", "Chickenstrips", 31, List.of("C#"));
        Employee employee3 = new Employee("Butawhiteboy", "Cantbekhan", 23, List.of("Java", "Haskell", "Scala"));
        Employee employee4 = new Employee("Dominique", "Cankersore", 42, List.of("PHP", "JavaScript", "React", "Angular"));
        Employee employee5 = new Employee("Butawhiteboy", "Cantbekhan", 38, List.of("Java", "Scala"));
        Employee employee6 = new Employee("Benedict Timothy", "Carlton Cumberbatch", 30, List.of("C", "C++"));
        Employee employee7 = new Employee("Honkytonk", "Cumbershack", 27, List.of("Rust", "Python"));
        Employee employee8 = new Employee("Botany", "Nottinghill", 51, List.of("Java", "Spring", "Hibernate", "Java"));

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);
    }

    @Test
    public void firstStream() {
        employees.forEach(System.out::println);
    }

    @Test
    public void mapOperation() {
        employees.stream()
                .map(employee -> employee.getFirstName())
                .forEach(System.out::println);
    }

    @Test
    public void mapOperationWithReference() {
        employees.stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    @Test
    public void mapOperation2() {
        employees.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .forEach(System.out::println);
    }

    @Test
    public void flatMapOperation() {
        final List<List<String>> allSkills = employees.stream()
                .map(Employee::getSkills)
                .collect(Collectors.toList());
        System.out.println(allSkills);
        final List<String> allSkills2 = employees.stream()
                .map(Employee::getSkills)
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(allSkills2);
    }

    @Test
    public void filterOperation() {
        employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .forEach(System.out::println);
    }

    @Test
    public void sortedOperationByAge() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .forEach(System.out::println);
    }

    @Test
    public void sortedOperationByLastName() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .forEach(System.out::println);
    }

    @Test
    public void limitOperation() {
        employees.stream()
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void limitSortOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void skipOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getLastName))
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void countOperation() {
        final long numberOfEmployees = employees.stream()
                .count();
        System.out.println(numberOfEmployees);
    }

    @Test
    public void countOperationWithBLetter() {
        final long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .count();
        System.out.println(numberOfEmployees);
    }

}
