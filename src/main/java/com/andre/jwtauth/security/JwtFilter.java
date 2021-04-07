package com.andre.jwtauth.security;

import com.andre.jwtauth.security.model.JwtObject;
import com.andre.jwtauth.security.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token =  request.getHeader(JwtUtils.HEADER_AUTHORIZATION);

        if(token!=null && !token.isEmpty()) {
            JwtObject object = JwtUtils.object(token);

            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(object.getSubject(), null, object.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(userToken);

        }else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

}
