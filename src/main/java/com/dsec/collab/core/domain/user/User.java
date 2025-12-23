package com.dsec.collab.core.domain.user;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String email;
    private final String name;

    private final boolean githubConnected;
    private final long githubId;
    private final String githubUser;
    private final String githubUrl;
    private final String githubAvatarUrl;

    private final boolean isAdmin;
    private final boolean isMember;

    private User(UUID id, String email, String name, boolean githubConnected, long githubId, String githubUser, String githubUrl, String githubAvatarUrl, boolean isModerator, boolean isMember) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.githubConnected = githubConnected;
        this.githubId = githubId;
        this.githubUser = githubUser;
        this.githubUrl = githubUrl;
        this.githubAvatarUrl = githubAvatarUrl;
        this.isAdmin = isModerator;
        this.isMember = isMember;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public boolean isGithubConnected() {
        return githubConnected;
    }

    public long getGithubId() {
        return githubId;
    }

    public String getGithubUser() {
        return githubUser;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getGithubAvatarUrl() {
        return githubAvatarUrl;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isMember() {
        return isMember;
    }

    public static User create(UUID id, String email, String name) {
        return new User(
            id,
            email,
            name,
            false,
            0,
            null,
            null,
            null,
            false,
            false
        );
    }

    public static User create(
        UUID id, String email, String name, boolean githubConnected,
        long githubId, String githubUser, String githubUrl, String
        githubAvatarUrl, boolean isModerator, boolean isMember
    ) {
        return new User(
            id, email, name, githubConnected, githubId, githubUser, githubUrl, githubAvatarUrl, isModerator, isMember
        );
    }

}