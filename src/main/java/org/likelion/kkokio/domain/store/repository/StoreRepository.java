package org.likelion.kkokio.domain.store.repository;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByAdminAccountAndDeletedAtIsNull(AdminAccount adminAccount);
    Optional<Store> findByAdminAccountAndStoreIdAndDeletedAtIsNull(AdminAccount adminAccount, Long storeId);

    @Query("SELECT s FROM Store s JOIN s.adminAccount a WHERE s.deletedAt IS NULL AND a.deletedAt IS NULL AND s.storeId = :storeId AND a.accountId = :accountId")
    Optional<Store> findByStoreIdAndAccountIdAndDeletedAtIsNull(Long storeId, Long accountId);
}
