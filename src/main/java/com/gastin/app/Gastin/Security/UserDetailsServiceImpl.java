package com.gastin.app.Gastin.Security;

import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email).orElseThrow(()-> new UsernameNotFoundException("El usuario con email " + email + "no existe."));
        return new UserDetailsImpl(user);
    }
}
