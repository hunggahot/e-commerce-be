package com.example.ecommercebe.service.role;

import com.example.ecommercebe.entity.Permission;
import com.example.ecommercebe.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Long roleId, Role updatedRole);
    void deleteRole(Long roleId);
    List<Role> getAllRoles();
    Role getRoleByID(Long roleId);

    void togglePermission(Long roleId, Permission permission, boolean isEnabled);
}
