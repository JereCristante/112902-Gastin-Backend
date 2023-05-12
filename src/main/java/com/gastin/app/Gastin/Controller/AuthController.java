package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.JwtDTO;
import com.gastin.app.Gastin.DTO.LoginUserDTO;
import com.gastin.app.Gastin.DTO.NewUserDTO;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Security.UserDetailsImpl;
import com.gastin.app.Gastin.Security.jwt.JwtProvider;
import com.gastin.app.Gastin.Service.UserService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    JwtProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUserDTO loginUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Contraseña o email invalidos", HttpStatus.BAD_REQUEST);
        }
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(),loginUserDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt,"Bearer",userDetails.getUsername());
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUserDTO newUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos Incorrectos o email invalido", HttpStatus.BAD_REQUEST);
        }
        if(userService.existByEmail(newUserDTO.getEmail())) {
            return new ResponseEntity<>("Ese email ya tiene una cuenta creada", HttpStatus.BAD_REQUEST);
        }
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        User user = userService.createUser(newUserDTO);
        return new ResponseEntity<>("Usuario Creado con exito", HttpStatus.CREATED);
    }
}
