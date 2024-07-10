package org.likelion.kkokio.domain.adminAccount.repository;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {
}
