package org.likelion.kkokio.domain.extra.repository;

import org.likelion.kkokio.domain.extra.entity.Extra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
    @Query("SELECT e FROM Extra e " +
            "JOIN e.menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND m.deletedAt IS NULL AND e.deletedAt IS NULL " +
            "AND e.extraId = :extraId AND a.accountId = :accountId")
    Optional<Extra> findByIdAndAdminIdAndDeleteAtIsNull(Long extraId, Long accountId);

    @Query("SELECT e FROM Extra e " +
            "JOIN e.menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND m.deletedAt IS NULL AND e.deletedAt IS NULL " +
            "AND e.extraId = :extraId")
    Optional<Extra> findByIdAndDeletedAtIsNull(Long extraId);

    @Query("SELECT e FROM Extra e " +
            "JOIN e.menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND m.deletedAt IS NULL AND e.deletedAt IS NULL " +
            "AND m.menuId = :menuId")
    Page<Extra> findByMenuIdAndDeletedAtIsNull(Long menuId, Pageable pageable);
}
