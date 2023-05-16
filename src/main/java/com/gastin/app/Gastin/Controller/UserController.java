package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.UserDTO;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.UserRepository;
import com.gastin.app.Gastin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService UserService;

    @GetMapping
    public List<UserDTO> getUsers(){
        return UserService.getAllUsers();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(UserService.getUserById(id));
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO UserDTO){
        return new ResponseEntity<>(UserService.createUser(UserDTO), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO UserDTO,@PathVariable(name = "id") long id){
        UserDTO userResponse= UserService.updateUser(UserDTO,id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);

    }
    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") long id){
        UserService.deleteUser(id);
        return new ResponseEntity<>("Usuario eliminado", HttpStatus.OK);
    }
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(UserService.getInfoByEmail(email));
    }
}
