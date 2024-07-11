package org.likelion.kkokio.domain.store.repository;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByAdminAccountAndDeletedAtIsNull(AdminAccount adminAccount);
    Optional<Store> findByAdminAccountAndStoreIdAndDeletedAtIsNull(AdminAccount adminAccount, Long storeId);
}
