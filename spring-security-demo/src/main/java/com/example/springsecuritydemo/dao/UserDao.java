package com.example.springsecuritydemo.dao;

import com.example.springsecuritydemo.entity.User;

import java.util.List;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);

	public List<User> getAllUsers();
}
