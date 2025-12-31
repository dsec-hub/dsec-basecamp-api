package com.dsec.collab.adaptor.http;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.GithubProfile;
import com.dsec.collab.core.port.IGithubProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class GithubProxy implements IGithubProxy {

    @Value("${spring.security.oauth2.client.github.client-id}")
    private String githubClientId;

    @Value("${spring.security.oauth2.client.github.client-secret}")
    private String githubClientSecret;

    private final RestTemplate restTemplate;

    public GithubProxy() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    // todo: proxy map to domain
    public GithubProxyUserProfile tokenExchange(String code) {
        // github url to exchange code for token
        URI uri = URI.create("https://github.com/login/oauth/access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", githubClientId);
        parameters.put("client_secret", githubClientSecret);
        parameters.put("code", code);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(parameters, headers);

        return restTemplate.postForObject(uri, request, GithubProxyAccessToken.class);
    }

    @Override
    public GithubProfile queryAuthenticatedUser(GithubAccessToken token) {
        URI uri = URI.create("https://api.github.com/user");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccessToken());
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(uri, HttpMethod.GET, entity, GithubProxyUserProfile.class).getBody();
    }

    @Override
    public String getRepositoryLink(long repositoryId) {
        return "";
    }

}
