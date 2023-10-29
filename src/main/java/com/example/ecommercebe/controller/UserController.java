package com.example.ecommercebe.controller;

import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.RoleException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.service.role.RoleService;
import com.example.ecommercebe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfileByJwt(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody User updatedUser) {
        try {
            String token = jwt.replace("Bearer ", "");
            User updatedUserProfile = userService.updateUserProfileByJwt(token, updatedUser);

            return ResponseEntity.ok(updatedUserProfile);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{userId}/roles/assign")
    public ResponseEntity<String> assignRolesToUser(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        try {
            userService.assignRolesToUser(userId, roleIds);
            return ResponseEntity.ok("Roles assigned to user successfully.");
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found.");
        } catch (RoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Role not found.");
        }
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<?> removeRolesFromUser(
            @PathVariable Long userId,
            @RequestBody List<Long> roleIds) {
        try {
            userService.removeRolesFromUser(userId, roleIds);
            return ResponseEntity.ok("Roles removed from the user successfully.");
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found.");
        } catch (RoleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Role not found.");
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
