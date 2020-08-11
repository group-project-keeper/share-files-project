package org.sharefiles.root.services;

import org.sharefiles.root.additions.ArgonPasswordEncoder;
import org.sharefiles.root.model.User;
import org.sharefiles.root.repository.UserRepository;
import org.sharefiles.root.responses.ResponseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AuthenticationManagerService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArgonPasswordEncoder argonPasswordEncoder;

    @Autowired
    private JWTResolverService jwtResolverService;


    // TODO: test @Service method, which takes care of registering user

    public void registerUser(User user) {
        user.setPassword(argonPasswordEncoder.encode(user.getPassword()));
        user.setUploadDirName(UUID.randomUUID().toString());
        userRepository.save(user);
    }


    // TODO: test @Service method, which takes care of logging in user

    public ResponseToken signInUser(String username, String password) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        return new ResponseToken(jwtResolverService.getToken(userDetails));
    }


}
