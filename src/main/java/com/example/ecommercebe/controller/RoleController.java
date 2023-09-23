package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.Permission;
import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.service.role.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        Role role = roleService.getRoleByID(roleId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, @RequestBody Role updatedRole) {
        Role role = roleService.updateRole(roleId, updatedRole);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{roleId}/togglePermission")
    public ResponseEntity<?> togglePermission(
            @PathVariable Long roleId,
            @RequestParam Permission permission,
            @RequestParam boolean isEnabled) {
        roleService.togglePermission(roleId, permission, isEnabled);
        return ResponseEntity.ok("Permission toggled successfully");
    }

}
