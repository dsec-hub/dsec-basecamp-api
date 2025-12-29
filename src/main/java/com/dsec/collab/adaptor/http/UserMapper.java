package com.dsec.collab.adaptor.http;

import com.dsec.collab.core.domain.User;
import com.dsec.collab.core.domain.GithubProfile;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO getUserDTO(User user) {
        GithubProfile profile = user.getGithubProfile();

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.isGithubConnected(),
                profile != null ? profile.getId() : null,
                profile != null ? profile.getUsername() : null,
                profile != null ? profile.getUrl() : null,
                profile != null ? profile.getAvatarUrl() : null
        );
    }
}