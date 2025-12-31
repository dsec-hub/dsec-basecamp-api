package com.dsec.collab.core.service;

import com.dsec.collab.core.domain.User;

public interface TokenRefresherApi {
    public void validateToken(User user);
}
