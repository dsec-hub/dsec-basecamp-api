package com.dsec.collab.adaptor.http.user;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String email,
        String username
) {}
