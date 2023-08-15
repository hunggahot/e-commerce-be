package com.example.ecommercebe.service;

import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
