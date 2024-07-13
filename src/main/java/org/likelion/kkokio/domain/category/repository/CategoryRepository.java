package org.likelion.kkokio.domain.category.repository;

import org.likelion.kkokio.domain.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND" +
            " s.storeId = :storeId AND c.categoryName = :categoryName")
    Optional<Category> findByCategoryNameAndStoreId(Long storeId, String categoryName);

    @Query("SELECT c FROM Category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND" +
            " a.accountId = :accountId AND c.categoryId = :categoryId")
    Optional<Category> findbIdAndAdminAccountIdDeletedAtIsNULL(Long accountId, Long categoryId);

    @Query("SELECT c, s FROM Category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL " +
            "AND s.storeId = :storeId AND a.accountId = :accountId")
    Page<Object[]> findByStoreIdAndDeletedAt(Long storeId, Long accountId, Pageable pageable);

    @Query("SELECT c, s FROM Category c JOIN c.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL AND" +
            " a.accountId = :accountId AND c.categoryId = :categoryId")
    Optional<Category> findbIdAndAdminAccountIdDeletedAtIsNullJoinStore(Long accountId, Long categoryId);

    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Long categoryId);
}
