package com.dsec.collab.core.service;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.User;
import com.dsec.collab.core.port.IGithubProxy;
import com.dsec.collab.core.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenRefresherService implements TokenRefresherApi {

    private final UserRepository userRepository;
    private final IGithubProxy githubProxy;

    public TokenRefresherService(UserRepository userRepository, IGithubProxy githubProxy) {
        this.userRepository = userRepository;
        this.githubProxy = githubProxy;
    }

    @Override
    public void validateToken(User user) {

        System.out.println("GithubProxy refreshToken");
        System.out.println("Current (OLD) token: " + user.getGithubAccessToken().getAccessToken());

        if (!user.hasValidToken()) {

            System.out.println("USER DOES NOT HAVE VALID TOKEN");

            GithubAccessToken newToken = githubProxy.refreshToken(user.getGithubAccessToken());
            user.setGithubAccessToken(newToken);

            System.out.println("CREATED NEW TOKEN" + user.getGithubAccessToken().getAccessToken());

            userRepository.save(user);
        }
    }
}
