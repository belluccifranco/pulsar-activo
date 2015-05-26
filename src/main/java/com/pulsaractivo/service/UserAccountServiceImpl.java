package com.pulsaractivo.service;

import com.pulsaractivo.model.UserAccount;
import com.pulsaractivo.model.UserRole;
import com.pulsaractivo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {

    private final UserAccountRepository accountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account;
        try {
            account = accountRepository.findByUsernameIgnoreCase(username);
        } catch (NoResultException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
        return this.buildUser(account);
    }

    private User buildUser(UserAccount userAccount) {
        String username = userAccount.getUsername();
        String password = userAccount.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole role : userAccount.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        User user = new User(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
