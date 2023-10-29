package com.example.ecommercebe.service.role;

import com.example.ecommercebe.entity.Permission;
import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.exception.RoleException;
import com.example.ecommercebe.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImplementation implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long roleId, Role updatedRole) {
        Role existingRole = getRoleByID(roleId);

        if(existingRole == null) {
            throw new RoleException("Role not found");
        }

        existingRole.setName(updatedRole.getName());
        existingRole.setDescription(updatedRole.getDescription());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role existingRole = getRoleByID(roleId);
        roleRepository.delete(existingRole);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByID(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleException("Role not found"));
    }

    @Override
    public void togglePermission(Long roleId, Permission permission, boolean isEnabled) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleException("Role not found"));

        // Check if the permission is already enabled or disabled
        boolean isPermissionEnabled = role.getPermissions().contains(permission);

        if (isEnabled && !isPermissionEnabled) {
            // Enable the permission
            role.getPermissions().add(permission);
        } else if (!isEnabled && isPermissionEnabled) {
            // Disable the permission
            role.getPermissions().remove(permission);
        }

        roleRepository.save(role);
    }

}
