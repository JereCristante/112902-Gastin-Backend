package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
