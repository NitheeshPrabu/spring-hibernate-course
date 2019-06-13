package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dao.RoleDao;
import com.example.springsecuritydemo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	@Transactional
	public Role findRoleByName(String roleName) {
		return roleDao.findRoleByName(roleName);
	}
}
