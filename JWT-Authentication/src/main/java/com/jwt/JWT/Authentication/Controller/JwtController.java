package com.jwt.JWT.Authentication.Controller;

import com.jwt.JWT.Authentication.Model.JwtRequest;
import com.jwt.JWT.Authentication.Model.JwtResponse;
import com.jwt.JWT.Authentication.Services.CustomerUserDetailsService;
import com.jwt.JWT.Authentication.helper.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        catch (BadCredentialsException e){
            e.printStackTrace();
            throw  new Exception("Bad Credentials");
        }
        UserDetails userDetails =this.customerUserDetailsService.loadUserByUsername(jwtRequest.getName());
        String token =this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT"+ token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
