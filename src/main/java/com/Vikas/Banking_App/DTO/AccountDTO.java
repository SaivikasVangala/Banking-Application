package com.Vikas.Banking_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {

    private long id;

    private String AccountHolderName;

    private double balance;

}
