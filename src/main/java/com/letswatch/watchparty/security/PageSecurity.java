package com.letswatch.watchparty.security;

import com.letswatch.watchparty.models.UserEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PageSecurity {
    public static String getUserSession(){
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if((authentication instanceof UserEntity)) {
            String currentUserUsername = ((UserEntity)authentication).getUsername();
            return currentUserUsername;
        }
        return null;
    }
}
