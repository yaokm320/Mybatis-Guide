package com.example.mapper;

import com.example.domain.User;

import java.util.List;

public interface UserMapper {

    public void save(User user);
    public List<User> findAll();
    public User findByID(int id);

}
