package com.example.Hexagonal_architecture_pattern.mapper;

import com.example.Hexagonal_architecture_pattern.dto.User;
import com.example.Hexagonal_architecture_pattern.port.UserPort;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends UserPort {
    @Override
    @Insert("INSERT INTO public.user (username, email, state) VALUES (#{username}, #{email}, #{state})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);

    @Override
    @Select("SELECT * FROM public.user WHERE id = #{id}")
    User getUser(int id);

    @Override
    @Update("UPDATE public.user SET username = #{username}, email = #{email} WHERE id = #{id}")
    void updateUser(User user);

    @Override
    @Delete("DELETE FROM public.user WHERE id = #{id}")
    void deleteUser(int id);
}