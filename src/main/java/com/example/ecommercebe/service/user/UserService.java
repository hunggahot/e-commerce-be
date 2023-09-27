package com.example.ecommercebe.service.user;

import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.UserException;

public interface UserService {

    User findUserById(Long userId) throws UserException;


    User findUserProfileByJwt(String jwt) throws UserException;

    User updateUserProfileByJwt(String jwt, User updatedUser) throws UserException;

    void addRoleToUser(User user, Role role) throws UserException;

    void removeRoleFromUser(User user, Role role) throws UserException;

    void verifyEmail(String token) throws UserException;
}
