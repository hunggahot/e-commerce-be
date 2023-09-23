package com.example.ecommercebe.service.user;

import com.example.ecommercebe.config.JwtProvider;
import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }

        throw new UserException("User not found with id: " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email " + email);
        }

        return user;
    }

    @Override
    public void addRoleToUser(User user, Role role) throws UserException {
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        } else {
            throw new UserException("User already has the role: " + role.getName());
        }
    }

    @Override
    public void removeRoleFromUser(User user, Role role) throws UserException {
        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
        } else {
            throw new UserException("User does not have the role: " + role.getName());
        }
    }

    @Override
    public void verifyEmail(String token) throws UserException {
    }
}
