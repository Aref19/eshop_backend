package com.example.eshop_backend;


import com.example.eshop_backend.request.PageReq;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class Until {

    public static Pageable createPageable(PageReq pageReq) {
        return PageRequest.of(pageReq.getPage(), pageReq.getSize(), (pageReq.getSort() == null) ? Sort.by(Sort.Direction.ASC): pageReq.getSort());
    }

    public static String emailProvider() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object name = authentication.getPrincipal();
        Map<String, Object> maps;
        Object userEmail = " ";
        if (name instanceof Jwt) {
            maps = ((Jwt) name).getClaims();
            userEmail = maps.get("email");
        }
        return userEmail.toString();
    }

}
