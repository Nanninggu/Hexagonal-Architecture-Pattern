package com.example.Hexagonal_architecture_pattern.mapper;

import com.example.Hexagonal_architecture_pattern.dto.Department;
import com.example.Hexagonal_architecture_pattern.port.DepartmentPort;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper extends DepartmentPort {
    @Insert("INSERT INTO public.departments(name, location) VALUES(#{name}, #{location})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addDepartment(Department department);

    @Select("SELECT * FROM public.departments WHERE id = #{id}")
    Department getDepartment(int id);

    @Update("UPDATE public.departments SET name = #{name}, location = #{location} WHERE id = #{id}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM public.departments WHERE id = #{id}")
    void deleteDepartment(int id);
}
