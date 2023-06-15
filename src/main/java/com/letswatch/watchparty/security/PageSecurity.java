package com.letswatch.watchparty.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PageSecurity {
    public static String getUserSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserUsername = authentication.getName();
            System.out.println(authentication);
            System.out.println(currentUserUsername);
            return currentUserUsername;

        }
        return null;
    }
}
