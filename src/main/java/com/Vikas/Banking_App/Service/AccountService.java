package com.Vikas.Banking_App.Service;

import com.Vikas.Banking_App.DTO.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDto);

    AccountDTO getAccountById(Long id);

    AccountDTO deposit(long id, double amount);

    AccountDTO withdraw(long id, double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long id);
}
