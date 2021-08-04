package com.company.random;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private String firstName;
    private String lastName;
    int age;

    private List<String> skills;
}
