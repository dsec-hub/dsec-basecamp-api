package com.dsec.collab.core.port;

import com.dsec.collab.core.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface UserApi {
    public User getOrCreateUser(UUID id, String email, String name);
    public User putUser(String id, String email, String username);
    public List<User> getAllUsers();

}