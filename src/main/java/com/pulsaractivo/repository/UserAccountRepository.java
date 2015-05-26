package com.pulsaractivo.repository;

import com.pulsaractivo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsernameIgnoreCase(String username);

}
