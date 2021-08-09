package com.company.random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void skipOperation2() {
        employees.stream().sorted(Comparator.comparing(Employee::getLastName))
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void countOperation2() {
        long numberOfEmployees = employees.stream()
                .filter(employee -> employee.getFirstName().startsWith("B"))
                .count();

        System.out.println(numberOfEmployees);
    }

    @Test
    public void minMaxOperations() {
        final Employee youngestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getAge)).get();
        System.out.println(youngestEmployee);
    }

    @Test
    public void maxMinOperations() {
        final Optional<Employee> oldestEmployee = Optional.of(employees.stream()
                .max(Comparator.comparing(Employee::getAge)).get());
        System.out.println(oldestEmployee);
    }

    @Test
    public void findAnyFindFirstOperations() {
        final Employee employee = employees.stream()
                .filter(emp -> emp.getFirstName().startsWith("B"))
                .findFirst().get();
        System.out.println(employee);
    }

    @Test
    public void findAnyFindFirstOperationsAny() {
        final Employee employee = employees.stream()
                .filter(emp -> emp.getFirstName().startsWith("B"))
                .findAny().get();
        System.out.println(employee);
    }

    @Test
    public void matchOperations() {
        final boolean b = employees.stream()
                .allMatch(emp -> emp.getFirstName().startsWith("B"));

        System.out.println(b);
    }

    @Test
    public void matchOperations2() {
        final boolean b = employees.stream()
                .allMatch(emp -> emp.getFirstName().contains("a"));

        System.out.println(b);
    }

    @Test
    public void matchOperations3() {
        final boolean b = employees.stream()
                .anyMatch(emp -> emp.getFirstName().contains("a"));

        System.out.println(b);
    }

    @Test
    public void matchOperations4() {
        final boolean b = employees.stream()
                .noneMatch(emp -> emp.getFirstName().startsWith("Z"));

        System.out.println(b);
    }

    @Test
    public void reduce() {
        final Integer sumOfAllAges = employees.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();
        System.out.println(sumOfAllAges);
    }

    @Test
    public void reduce2() {
        final Integer sumOfAllAges2 = employees.stream()
                .map(Employee::getAge)
                .reduce(0, Integer::sum);
        System.out.println(sumOfAllAges2);
    }

    @Test
    public void reduce3() {
        final Integer sumOfAllAges3 = employees.stream()
                .reduce(0, (age, employee) -> age + employee.getAge(), Integer::sum);
        System.out.println(sumOfAllAges3);
    }

    @Test
    public void addCommas() {
        final String allNames = employees.stream()
                .map(Employee::getFirstName)
                .reduce((name, name2) -> name + ", " + name2)
                .get();

        System.out.println(allNames);
    }

    @Test
    public void takeWhileOperation() {
        final Stream<Employee> employeeStream = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .takeWhile(employee -> employee.getAge() < 30);
        employeeStream
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileOperation() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .dropWhile(employee -> employee.getAge() < 30)
                .forEach(System.out::println);
    }

    @Test
    public void forEachOrdered() {
        String sentence = "Jak nauczyc sie programowania";
        sentence.chars().forEach(s -> System.out.print((char) s));
        System.out.println();
        sentence.chars().parallel().forEach(s -> System.out.print((char) s));
        System.out.println();
        sentence.chars().parallel().forEachOrdered(s -> System.out.print((char) s));
    }

    //anti-pattern -> not use it anymore
    @Test
    public void peekOperation() {
        final List<Employee> newEmployees = employees.stream()
                .peek(employee -> employee.setFirstName("Kamil"))
                .collect(Collectors.toList());
        System.out.println(newEmployees);
        System.out.println();
        System.out.println(employees);
    }


}
