package com.pulsaractivo.service;

import com.pulsaractivo.model.UserAccount;
import com.pulsaractivo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository accountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getUserAccountByUsername(String username) {
        return accountRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public boolean validateUserAccount(UserAccount userAccount) {
        UserAccount ua = this.getUserAccountByUsername(userAccount.getUsername());
        if (ua != null && ua.getPassword().equals(userAccount.getPassword())) {
            return true;
        }
        return false;
    }
}
