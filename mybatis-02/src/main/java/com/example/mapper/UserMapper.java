package com.example.mapper;

import com.example.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public List<User> findByCondition(User user);
    public List<User> findByIDs(List<Integer> ids);
}
