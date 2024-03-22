package com.example.employee.service;

import com.example.employee.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    void addDepartment(Department department);
    List<Department> getDepartmentByNameAndMinId(String name, int minId);
    void updateDepartment(Department department);
    void deleteDepartment(int id);
}
