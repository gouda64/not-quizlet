package com.gouda.notquizlet.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class OAuth2UserImpl implements OAuth2User  {
    private OAuth2User oAuth2User;

    public OAuth2UserImpl(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("email");
    }

    public String getFullName() {
        return oAuth2User.getAttribute("name");
    }
    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }
}
