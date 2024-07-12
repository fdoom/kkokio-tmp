package org.likelion.kkokio.domain.adminAccount.repository;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
    Optional<AdminAccount> findByAccountLoginId(String accountLoginId);
}
