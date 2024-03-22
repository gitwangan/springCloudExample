package com.example.employee.repo;

import com.example.employee.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository {
    void save(Department department);
    List<Department> findByNameAndMinId(String name, int minId);
    void update(Department department);
    void delete(int id);
}
