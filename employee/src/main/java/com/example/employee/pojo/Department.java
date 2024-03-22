package com.example.employee.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

import java.util.List;

@Data
@Entity
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employeeList;
    @Transient
    private List<String> employeeNames;
}
