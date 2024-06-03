package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.User;
import com.example.Hexagonal_architecture_pattern.mapper.UserMapper;
import com.example.Hexagonal_architecture_pattern.port.UserPort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserPort {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}

