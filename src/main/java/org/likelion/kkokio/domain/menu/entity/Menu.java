package org.likelion.kkokio.domain.menu.entity;

import jakarta.persistence.*;
import org.likelion.kkokio.domain.category.entity.Category;
import org.likelion.kkokio.domain.ordersMenu.entity.OrdersMenu;
import org.likelion.kkokio.global.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "menu_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "menu_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "menu_delete_at"))
})
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Long menuPrice;

    private String menuSummary;

    private String menuImgUrl;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<OrdersMenu> ordersMenu = new ArrayList<>();

}
