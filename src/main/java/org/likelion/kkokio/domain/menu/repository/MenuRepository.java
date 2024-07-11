package org.likelion.kkokio.domain.menu.repository;

import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m, c FROM Menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE m.deletedAt IS NULL AND c.deletedAt IS NULL AND s.deletedAt IS NULL AND a.deletedAt IS NULL " +
            "AND m.menuId = :menuId AND a.accountId = :accountId")
    Optional<Menu> findByIdALLDeletedAtIsNull(Long menuId, Long accountId);

    @Query("SELECT m, c FROM Menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE m.deletedAt IS NULL AND c.deletedAt IS NULL AND s.deletedAt IS NULL " +
            "AND s.storeId = :storeId")
    Page<Object[]> getMenuInfoStoreId(Long storeId, Pageable pageable);

    @Query("SELECT m, c FROM Menu m JOIN m.category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE m.deletedAt IS NULL AND c.deletedAt IS NULL AND s.deletedAt IS NULL " +
            "AND s.storeId = :storeId AND c.categoryId = :categoryId")
    Page<Object[]> getMenuInfoStoreIdAndcategoryId(Long storeId, Long categoryId, Pageable pageable);


}