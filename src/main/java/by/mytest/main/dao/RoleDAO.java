package by.mytest.main.dao;

import java.util.List;

import by.mytest.main.model.Role;

public interface RoleDAO {
	public List<Role> getRoleList();

	public void saveRole(Role role);

	public Role getRole(int idRole);

	public void deleteRole(int idRole);
}
