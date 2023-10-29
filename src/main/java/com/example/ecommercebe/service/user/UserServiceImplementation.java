package com.example.ecommercebe.service.user;

import com.example.ecommercebe.config.JwtProvider;
import com.example.ecommercebe.entity.Role;
import com.example.ecommercebe.entity.User;
import com.example.ecommercebe.exception.RoleException;
import com.example.ecommercebe.exception.UserException;
import com.example.ecommercebe.repository.RoleRepository;
import com.example.ecommercebe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() throws UserException {
        return userRepository.findAll();
    }

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
    public User updateUserProfileByJwt(String jwt, User updatedUser) throws UserException {
        // Extract email from the JWT
        String email = jwtProvider.getEmailFromToken(jwt);

        // Find the user by email
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email " + email);
        }

        // Update user's profile with the provided data
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());
        // Add other profile fields as needed

        // Save the updated user

        return userRepository.save(user);
    }

    @Override
    public void assignRolesToUser(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));

        Set<Role> roles = roleIds.stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new RoleException("Role not found")))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public void removeRolesFromUser(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));

        Set<Long> roleIdsSet = new HashSet<>(roleIds);
        Set<Role> rolesToRemove = new HashSet<>();

        for (Long roleId : roleIdsSet) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RoleException("Role not found"));
            rolesToRemove.add(role);
        }

        user.getRoles().removeAll(rolesToRemove);

        userRepository.save(user);
    }

    @Override
    public void verifyEmail(String token) throws UserException {
    }
}
