package com.example.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class Department {

    private int id;
    private String name;
    private List<Employee> employeeList;
    private List<String> employeeNames;
}
