package org.seledynowapowieka.simpleMVC.dao;

import org.seledynowapowieka.simpleMVC.entities.Role;

public interface RoleDao {
	public Role findRoleByName(String roleName);
}
