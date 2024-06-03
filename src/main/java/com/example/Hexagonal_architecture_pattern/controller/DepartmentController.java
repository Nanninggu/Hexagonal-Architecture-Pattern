package com.example.Hexagonal_architecture_pattern.controller;

import com.example.Hexagonal_architecture_pattern.dto.Department;
import com.example.Hexagonal_architecture_pattern.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable int id) {
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        departmentService.updateDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
