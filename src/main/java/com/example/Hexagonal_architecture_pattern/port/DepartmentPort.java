package com.example.Hexagonal_architecture_pattern.port;

import com.example.Hexagonal_architecture_pattern.dto.Department;

public interface DepartmentPort {
    void addDepartment(Department department);
    Department getDepartment(int id);
    void updateDepartment(Department department);
    void deleteDepartment(int id);
}