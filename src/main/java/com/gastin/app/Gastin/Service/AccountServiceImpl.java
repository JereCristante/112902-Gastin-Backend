package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Model.Movement;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.AccountRepository;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountDTO createAccount(Long usuario_id, AccountDTO accountDTO) {
        Account account = entityMapping(accountDTO);
        User user = userRepository.findById(usuario_id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",usuario_id));
        account.setUser(user);
        Account newAccount = accountRepository.save(account);
        return dtoMapping(newAccount);
    }

    @Override
    public AccountDTO updateAccount( AccountDTO accountDTO, Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",id));
        account.setDescription(accountDTO.getDescription());
        if(accountDTO.getBalance()!=null){
            account.setBalance(accountDTO.getBalance());
        }
        Account newAccount = accountRepository.save(account);
        return dtoMapping(newAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cuenta","id",id));
        account.setActive(false);
        accountRepository.save(account);
    }

    private AccountDTO dtoMapping(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setDescription(account.getDescription());
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setActive(account.getActive());
        return accountDTO;
    }
    private Account entityMapping(AccountDTO accountDTO){
        Account account = new Account();
        account.setDescription(accountDTO.getDescription());
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        account.setActive(accountDTO.getActive());
        return account;
    }
}