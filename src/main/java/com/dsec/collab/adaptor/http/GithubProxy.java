package com.dsec.collab.adaptor.http;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.GithubProfile;
import com.dsec.collab.core.port.IGithubProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.*;

@Service
public class GithubProxy implements IGithubProxy {

    @Value("${spring.security.oauth2.client.github.client-id}")
    private String githubClientId;

    @Value("${spring.security.oauth2.client.github.client-secret}")
    private String githubClientSecret;

    private final RestClient restClient;

    public GithubProxy(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public GithubAccessToken tokenExchange(String code) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", githubClientId);
        parameters.add("client_secret", githubClientSecret);
        parameters.add("code", code);

        GithubProxyAccessToken proxyToken = restClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .contentType(APPLICATION_FORM_URLENCODED)
                .accept(APPLICATION_JSON)
                .body(parameters)
                .retrieve()
                .body(GithubProxyAccessToken.class);

        return toDomain(proxyToken);

    }

    @Override
    public GithubAccessToken refreshToken(GithubAccessToken token) {



        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", githubClientId);
        parameters.add("client_secret", githubClientSecret);
        parameters.add("grant_type", "refresh_token");
        parameters.add("refresh_token", token.getRefreshToken());

        GithubProxyAccessToken proxyToken = restClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .contentType(APPLICATION_FORM_URLENCODED)
                .accept(APPLICATION_JSON)
                .body(parameters)
                .retrieve()
                .body(GithubProxyAccessToken.class);

        return toDomain(proxyToken);
    }


    @Override
    public GithubProfile queryAuthenticatedUser(GithubAccessToken token) {

        GithubProxyUserProfile userProfile = restClient.get()
                .uri("https://api.github.com/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token.getAccessToken())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(GithubProxyUserProfile.class);

        return toDomain(userProfile);

    }

    @Override
    public String getRepositoryLink(GithubAccessToken token, long repositoryId) {
        return "";
    }

    private GithubAccessToken toDomain(GithubProxyAccessToken proxyToken) {
        return GithubAccessToken.create(
                proxyToken.accessToken(),
                proxyToken.expiresIn(),
                proxyToken.refreshToken(),
                proxyToken.refreshTokenExpiresIn(),
                proxyToken.scope(),
                proxyToken.tokenType()
        );
    }

    private GithubProfile toDomain(GithubProxyUserProfile proxyUserProfile) {
        return GithubProfile.create(
                    proxyUserProfile.githubId(),
                    proxyUserProfile.githubUsername(),
                    proxyUserProfile.githubUrl(),
                    proxyUserProfile.githubAvatarUrl()
                );
    }

}
