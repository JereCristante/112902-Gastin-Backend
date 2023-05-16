package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findByUserId(Long userId);
}
