package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.DTO.NewUserDTO;
import com.gastin.app.Gastin.DTO.UserDTO;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.AccountRepository;
import com.gastin.app.Gastin.Repository.CategoryRepository;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = entityMapping(userDTO);
        User newUser = userRepository.save(user);
        UserDTO userRespuesta = DTOmapping(newUser);
        //crear cuentas por defecto
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance((double) 0);
        accountDTO.setDescription("Efectivo");
        accountDTO.setActive(true);
        accountService.createAccount(newUser.getId(), accountDTO);
        //creas cuentas
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription("Varios");
        categoryService.createCategory(newUser.getId(), 1L,categoryDTO);
        categoryService.createCategory(newUser.getId(), 2L,categoryDTO);
        return userRespuesta;
    }
    @Override
    public User createUser(NewUserDTO newUserDTO) {
        User user = new User();
        user.setAlias(newUserDTO.getAlias());
        user.setEmail(newUserDTO.getEmail());
        user.setPassword(newUserDTO.getPassword());
        user.setActive(true);
        User newUser = userRepository.save(user);
        //UserDTO userRespuesta = DTOmapping(newUser);
        //crear cuentas por defecto
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance((double) 0);
        accountDTO.setDescription("Efectivo");
        accountDTO.setActive(true);
        accountService.createAccount(newUser.getId(), accountDTO);
        //creas cuentas
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription("Varios");
        categoryService.createCategory(newUser.getId(), 1L,categoryDTO);
        categoryService.createCategory(newUser.getId(), 2L,categoryDTO);
        return newUser;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> DTOmapping(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",id));
        return DTOmapping(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",id));
        user.setActive(false);
        userRepository.save(user);
        //userRepository.delete(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",id));
        user.setAlias(userDTO.getAlias());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setIdUpperUser(userDTO.getIdUpperUser());
        User updatedUser = userRepository.save(user);
        return DTOmapping(updatedUser);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findOneByEmail(email);
    }
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    private UserDTO DTOmapping(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAlias(user.getAlias());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setIdUpperUser(user.getIdUpperUser());
        userDTO.setActive(user.getActive());
        return userDTO;
    }
    private User entityMapping(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setAlias(userDTO.getAlias());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setIdUpperUser(userDTO.getIdUpperUser());
        user.setActive(userDTO.getActive());
        return user;
    }

}
