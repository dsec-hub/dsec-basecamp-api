package com.dsec.collab.adaptor.http.user;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String email,
    String name,

    boolean githubConnected,
    long githubId,
    String githubUser,
    String githubUrl,
    String githubAvatarUrl,

    boolean isAdmin,
    boolean isMember
) {}
