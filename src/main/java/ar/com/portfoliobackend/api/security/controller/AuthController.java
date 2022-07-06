package ar.com.portfoliobackend.api.security.controller;

import ar.com.portfoliobackend.api.security.dto.JwtDto;
import ar.com.portfoliobackend.api.security.dto.NewUser;
import ar.com.portfoliobackend.api.security.dto.UserLogin;
import ar.com.portfoliobackend.api.security.enums.RoleName;
import ar.com.portfoliobackend.api.security.jwt.JwtProvider;
import ar.com.portfoliobackend.api.security.model.Role;
import ar.com.portfoliobackend.api.security.model.User;
import ar.com.portfoliobackend.api.security.service.RoleService;
import ar.com.portfoliobackend.api.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> newUser(@Valid @RequestBody NewUser newUser,  
                                 BindingResult bindingResult ){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        User user = 
                new User(newUser.getName(), newUser.getUserName(), 
                         newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
}
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userLogin, 
                                        BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), 
                                                                      userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
        
        
    }
}
