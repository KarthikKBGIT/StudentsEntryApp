package com.karthik.StudentEntryApp.config;

import com.karthik.StudentEntryApp.service.CustomUserDetailsService;
import com.karthik.StudentEntryApp.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            log.info("Authorization header found, extracting token");
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            // If we found a username and the user isn't logged in yet
            if(username != null && jwtService.validateToken(token, userDetails)){
                log.info("Token is valid, setting authentication in Security Context");
                // Create an Authentication object for Spring's Security Context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationFilter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Update the Security Context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationFilter);
            }
        }
        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
