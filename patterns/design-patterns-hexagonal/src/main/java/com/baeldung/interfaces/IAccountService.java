package com.baeldung.interfaces;


import com.baeldung.model.Account;

import java.util.List;

public interface IAccountService {
    Account createAccount(Account account);
    Account getAccount(Long accountNumber);
    List<Account> allAccounts();
}
