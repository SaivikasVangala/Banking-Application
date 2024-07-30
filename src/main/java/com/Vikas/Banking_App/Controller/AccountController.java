package com.Vikas.Banking_App.Controller;

import com.Vikas.Banking_App.DTO.AccountDTO;
import com.Vikas.Banking_App.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/API/Accounts") //This is going to be the basic url for all the AccountController Class
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    //Here id is the template variable.
     @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);

        return  ResponseEntity.ok(accountDTO) ;
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable long id,@RequestBody Map<String,Double> request){
    //RequestBody will map request Json body into the map java object.
        double amount = request.get("amount");
        AccountDTO accountDTO =   accountService.deposit(id,amount);
        return  ResponseEntity.ok(accountDTO) ;
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable long id,@RequestBody Map<String,Double> request){
        double amount = request.get("amount");
        AccountDTO accountDTO = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDTO) ;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountDTOS = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account was successfully deleted") ;
    }


}
