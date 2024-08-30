package com.workoutsheet.workoutsheet.service;

import com.workoutsheet.workoutsheet.domain.Role;
import com.workoutsheet.workoutsheet.domain.User;
import com.workoutsheet.workoutsheet.domain.enumeration.RoleName;
import com.workoutsheet.workoutsheet.repository.RoleRepository;
import com.workoutsheet.workoutsheet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User createUser(String email, String password) {
        Optional<User> userExists = userRepository.findByEmail(email);
        if (userExists.isPresent()) {
            throw new RuntimeException("");
        }

        User user = User
                .builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(getDefaultRoles())
                .build();

        return userRepository.save(user);
    }

    private Set<Role> getDefaultRoles() {
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow();

        return Set.of(userRole);
    }
}
