package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/{usuarioId}/accounts")
    public ResponseEntity<AccountDTO> saveAccount(@PathVariable(value = "usuarioId")Long usuarioId, @RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(usuarioId,accountDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{cuentaId}/accounts")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable(value = "cuentaId")Long cuentaId, @RequestBody AccountDTO accountDTO){
        return new ResponseEntity<AccountDTO>(accountService.updateAccount(accountDTO,cuentaId), HttpStatus.OK);
    }
    @DeleteMapping("/{cuentaId}/accounts")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "cuentaId") long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Cuenta eliminada", HttpStatus.OK);
    }
    @GetMapping("/{usuarioId}/accounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(@PathVariable(value = "usuarioId")Long usuarioId){
        return new ResponseEntity<List<AccountDTO>>(accountService.findAccountsByUser(usuarioId), HttpStatus.OK);
    }
}
