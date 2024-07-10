package org.likelion.kkokio.domain.store.entity;

import jakarta.persistence.*;
import org.likelion.kkokio.domain.category.entity.Category;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.global.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "store_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "store_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "store_delete_at", updatable = false))
})
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    //Account 외래키 추가 예정

    @Column(nullable = false, length = 254)
    private String storeName;

    @Column(length = 254)
    private String storeSummary;

    private String storeAddress;

    private String profile_img_url;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Category> category = new ArrayList<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();
}
