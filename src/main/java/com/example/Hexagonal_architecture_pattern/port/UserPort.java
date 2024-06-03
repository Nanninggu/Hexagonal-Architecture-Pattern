package com.example.Hexagonal_architecture_pattern.port;

import com.example.Hexagonal_architecture_pattern.dto.User;

public interface UserPort {
    void addUser(User user);
    User getUser(int id);
    void updateUser(User user);
    void deleteUser(int id);
}