package org.likelion.kkokio.domain.store.repository;

import org.likelion.kkokio.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
