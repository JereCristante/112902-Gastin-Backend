package com.gastin.app.Gastin.DTO;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AccountListDTO {
    @Getter @Setter
    private AccountDTO account;
    @Getter @Setter @Transient
    private List<AccountMovementDTO> movements;

    public AccountListDTO() {
        this.account = null;
        this.movements = null;
    }
}
