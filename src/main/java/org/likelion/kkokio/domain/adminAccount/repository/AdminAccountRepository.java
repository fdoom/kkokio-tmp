package org.likelion.kkokio.domain.adminAccount.repository;

import jakarta.persistence.LockModeType;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
    Optional<AdminAccount> findByAccountLoginId(String accountLoginId);

    // SELECT FOR UPDATE
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT y FROM AdminAccount y WHERE y.accountId = :accountId")
    Optional<AdminAccount> findByIdWithLock(Long accountId);
}
