package com.gastin.app.Gastin.Repository;

import com.gastin.app.Gastin.DTO.AccountMovementDTO;
import com.gastin.app.Gastin.Model.Account;
import com.gastin.app.Gastin.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public List<Account> findByUserId(Long userId);

    @Query(nativeQuery = true,name = "AccountMovements")
    public List<AccountMovementDTO> getLastMovementsList(@Param("account") Long account);
}
