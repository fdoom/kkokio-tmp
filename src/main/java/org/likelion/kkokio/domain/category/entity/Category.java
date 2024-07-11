package org.likelion.kkokio.domain.category.entity;

import jakarta.persistence.*;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.global.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "category_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "category_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "category_delete_at"))
})
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_store_id_category_name", columnNames = {"categoryName", "store_id"})
})
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false, length = 150)
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<>();
}
