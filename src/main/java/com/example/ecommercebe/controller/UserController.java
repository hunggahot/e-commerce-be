package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.RoleException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.service.role.RoleService;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{userId}/roles/add")
    public ResponseEntity<String> addRoleToUser(
            @PathVariable Long userId,
            @RequestParam Long roleId) {
        try {
            User user = userService.findUserById(userId);
            Role role = roleService.getRoleByID(roleId);

            userService.addRoleToUser(user, role);

            return ResponseEntity.ok("Role added to user successfully.");
        } catch (UserException | RoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{userId}/roles/remove")
    public ResponseEntity<String> removeRoleFromUser(
            @PathVariable Long userId,
            @RequestParam Long roleId) {
        try {
            User user = userService.findUserById(userId);
            Role role = roleService.getRoleByID(roleId);

            userService.removeRoleFromUser(user, role);

            return ResponseEntity.ok("Role removed from user successfully.");
        } catch (UserException | RoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

}
