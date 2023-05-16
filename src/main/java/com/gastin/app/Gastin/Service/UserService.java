package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.NewUserDTO;
import com.gastin.app.Gastin.DTO.UserDTO;
import com.gastin.app.Gastin.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserDTO createUser(UserDTO userDTO);
    public User createUser(NewUserDTO newUserDTO);
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long id);
    public void deleteUser(Long id);
    public UserDTO updateUser(UserDTO userDTO, Long id);
    public Optional<User> getByEmail(String email);
    public boolean existByEmail(String email);
    public Optional<User> getInfoByEmail(String email);
}
