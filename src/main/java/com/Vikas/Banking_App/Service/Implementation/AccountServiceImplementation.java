package com.Vikas.Banking_App.Service.Implementation;

import com.Vikas.Banking_App.DTO.AccountDTO;
import com.Vikas.Banking_App.Mapping.AccountMapping;
import com.Vikas.Banking_App.Model.Account;
import com.Vikas.Banking_App.Repository.AccountRepository;
import com.Vikas.Banking_App.Service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDto) {
   //RequestBody Consists of Json which converts json into the java object.
        Account account = AccountMapping.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapping.mapToAccountDTO(savedAccount);
    }
    //Here the Return type is Accountdto so we have to convert jpa entity object to accountdto
    @Override
    public AccountDTO getAccountById(Long id) {
        Account account =
                accountRepository.
                        findById(id).orElseThrow(() -> new RuntimeException("Account do not exist"));

        return AccountMapping.mapToAccountDTO(account);

    }

    @Override
    public AccountDTO deposit(long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(() -> new RuntimeException("Account do not exist"));

        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapping.mapToAccountDTO(savedAccount) ;
    }

    @Override
    public AccountDTO withdraw(long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(() -> new RuntimeException("Account do not exist"));

       if(account.getBalance() < amount){
           throw new RuntimeException("Insufficient balance");
       }

        double totalAmount = account.getBalance() - amount;
        account.setBalance(totalAmount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapping.mapToAccountDTO(savedAccount) ;

    }

    @Override
    public List<AccountDTO> getAllAccounts() {

       List<Account> accounts = accountRepository.findAll();
       //Now we have to convert list of accounts into AccountDto.


        return accounts.stream().map((account) -> AccountMapping.mapToAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.
                findById(id).orElseThrow(() -> new RuntimeException("Account do not exist"));

        accountRepository.deleteById(id);
    }


}
