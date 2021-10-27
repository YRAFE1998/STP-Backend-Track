package application.controller;

import application.jwt.JwtUtil;
import application.model.authentication.AuthenticationRequest;
import application.model.authentication.AuthenticationResponse;
import application.model.UserDetails;
import application.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword()));

        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or password", e);
        }

        final UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



}
