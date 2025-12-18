package com.dsec.collab.core.port;

import com.dsec.collab.core.domain.user.User;

import java.util.UUID;

public interface UserRepository {
    public User getUser(UUID id); // rehydrating user
    public User updateUser(UUID id, User user); // the id may never update
}
