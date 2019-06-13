package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.Role;

import java.util.List;

public interface RoleService {

	public List<Role> getAllRoles();

	public Role findRoleByName(String roleName);
}
