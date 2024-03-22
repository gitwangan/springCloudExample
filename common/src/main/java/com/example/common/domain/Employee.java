package com.example.common.domain;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private int age;
    private Department department;
}
