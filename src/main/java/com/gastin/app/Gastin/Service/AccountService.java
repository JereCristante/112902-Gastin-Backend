package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AccountService{
    public AccountDTO createAccount(Long usuario_id, AccountDTO accountDTO);
    public AccountDTO updateAccount(AccountDTO accountDTO, Long id);
    public void deleteAccount(Long id);
    public List<AccountDTO> findAccountsByUser(Long usuario_id);
}
