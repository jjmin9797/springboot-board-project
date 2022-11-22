package com.studio.projectboard.repository;

import com.studio.projectboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
}
