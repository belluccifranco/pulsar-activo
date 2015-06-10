package com.pulsaractivo.service;

import com.pulsaractivo.model.UserAccount;

public interface UserAccountService {

    UserAccount getUserAccountByUsername(String username);

    boolean validateUserAccount(UserAccount userAccount);

}
