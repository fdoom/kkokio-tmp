package org.likelion.kkokio.domain.store.repository;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByAdminAccountAndDeletedAtIsNull(AdminAccount adminAccount);
}
