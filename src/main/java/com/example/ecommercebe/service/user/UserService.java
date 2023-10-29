package com.example.ecommercebe.service.user;

import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.UserException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws UserException;

    User findUserById(Long userId) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;

    User updateUserProfileByJwt(String jwt, User updatedUser) throws UserException;

    void assignRolesToUser(Long userId, List<Long> roleIds);

    void removeRolesFromUser(Long userId, List<Long> roleIds);

    void verifyEmail(String token) throws UserException;
}
