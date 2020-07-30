package org.sharefiles.root.security;

import org.sharefiles.root.services.JWTResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JWTResolverService jwtResolverService;

    @Autowired
    private UserDetailsServiceImplementation userDetailsImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String bearerTokenHeader = httpServletRequest.getHeader("Authorization");

        try {
            if (StringUtils.hasText(bearerTokenHeader) && bearerTokenHeader.startsWith("Bearer ")) {
                String token = bearerTokenHeader.substring(7);

                if (jwtResolverService.isTokenValid(token)) {
                    UserDetails userDetails = userDetailsImpl.loadUserByUsername(jwtResolverService.getUsernameFromToken(token));
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

        } catch (Exception e) {
            logger.error("Cannot identify the token", e.getCause());
        }

        doFilter(httpServletRequest, httpServletResponse, filterChain);
    }


}
