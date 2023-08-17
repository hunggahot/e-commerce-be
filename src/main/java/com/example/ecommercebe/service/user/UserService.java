package com.example.ecommercebe.service.user;

import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.UserException;

public interface UserService {

    User findUserById(Long userId) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;
}
