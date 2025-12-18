package com.dsec.collab.core.domain.user;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private final String username;

    private User(UUID id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public static User UserFactory(UUID id, String email, String username) {
        return new User(id, email, username);
    }
}