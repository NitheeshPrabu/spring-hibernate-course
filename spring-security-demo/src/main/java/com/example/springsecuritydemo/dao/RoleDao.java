package com.example.springsecuritydemo.dao;

import com.example.springsecuritydemo.entity.Role;

import java.util.List;

public interface RoleDao {

	public Role findRoleByName(String roleName);

	public List<Role> getAllRoles();
}
