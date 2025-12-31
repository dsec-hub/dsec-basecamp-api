package com.dsec.collab.core.port;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.GithubProfile;

public interface IGithubProxy {
    GithubAccessToken tokenExchange(String code);
    GithubAccessToken refreshToken(GithubAccessToken accessToken);
    GithubProfile queryAuthenticatedUser(GithubAccessToken token);
    String getRepositoryLink(GithubAccessToken token, long repositoryId);
}
