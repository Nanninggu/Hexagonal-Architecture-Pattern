package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.Department;
import com.example.Hexagonal_architecture_pattern.mapper.DepartmentMapper;
import com.example.Hexagonal_architecture_pattern.port.DepartmentPort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements DepartmentPort {
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
    public Department getDepartment(int id) {
        return departmentMapper.getDepartment(id);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentMapper.deleteDepartment(id);
    }
}
