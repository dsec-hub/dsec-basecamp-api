package com.dsec.collab.core.port;

import com.dsec.collab.core.domain.GithubAccessToken;
import com.dsec.collab.core.domain.TenantUserProfile;

public interface TenantProxy {
    GithubAccessToken tokenExchange(String code);
    TenantUserProfile queryAuthenticatedUser(GithubAccessToken tenantToken);
}
