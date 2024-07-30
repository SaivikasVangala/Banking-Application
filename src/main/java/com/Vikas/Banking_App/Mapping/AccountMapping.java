package com.Vikas.Banking_App.Mapping;

import com.Vikas.Banking_App.DTO.AccountDTO;
import com.Vikas.Banking_App.Model.Account;

public class AccountMapping{


    public static Account mapToAccount(AccountDTO accountDTO){
       Account account = new Account(
       accountDTO.getId(),
       accountDTO.getAccountHolderName(),
       accountDTO.getBalance()
       );
       return account;
    }

    public static AccountDTO mapToAccountDTO(Account account){
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDTO;
    }
}
