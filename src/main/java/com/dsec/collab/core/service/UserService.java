package com.dsec.collab.core.service;

import com.dsec.collab.core.domain.user.User;
import com.dsec.collab.core.port.UserApi;
import com.dsec.collab.core.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserApi {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User getOrCreateUser(UUID id, String email, String name) {
       Optional<User> user = userRepository.findById(id);

       if (user.isPresent()) {
           System.out.println("Found user, returning them");
           return user.get();
       } else {
           System.out.println("User not found, creating new and returning");
           User newUser = User.create(id, email, name);
           return userRepository.save(newUser);
       }

    }

    @Override
    public User putUser(String id, String email, String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

}