package com.example.employee.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private int age;
    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;
}
