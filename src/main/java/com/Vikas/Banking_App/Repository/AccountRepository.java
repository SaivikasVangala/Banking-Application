package com.Vikas.Banking_App.Repository;

import com.Vikas.Banking_App.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
