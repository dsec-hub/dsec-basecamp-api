package com.dsec.collab.core.service;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.User;
import com.dsec.collab.core.port.IGithubProxy;
import com.dsec.collab.core.port.UserRepository;

public class TokenRefresherService implements TokenRefresherApi {

    private final UserRepository userRepository;
    private final IGithubProxy githubProxy;

    public TokenRefresherService(UserRepository userRepository, IGithubProxy githubProxy) {
        this.userRepository = userRepository;
        this.githubProxy = githubProxy;
    }

    @Override
    public void validateToken(User user) {
        if (!user.hasValidToken()) {
            GithubAccessToken newToken = githubProxy.refreshToken(user.getGithubAccessToken());
            user.setGithubAccessToken(newToken);
            userRepository.save(user);
        }
    }

}
