package com.example.employee.service.impl;

import com.example.employee.pojo.Department;
import com.example.employee.pojo.Employee;
import com.example.employee.repo.DepartmentRepository;
import com.example.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentByNameAndMinId(String name, int minId) {
        List<Department> departments = departmentRepository.findByNameAndMinId(name, minId);
        for (Department department : departments) {
            department.setEmployeeNames(
                    department.getEmployeeList().stream().map(Employee::getName).collect(Collectors.toList()));
        }
        return departments;
    }

    @Override
    @Transactional
    public void updateDepartment(Department department) {
        departmentRepository.update(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.delete(id);
    }

}
