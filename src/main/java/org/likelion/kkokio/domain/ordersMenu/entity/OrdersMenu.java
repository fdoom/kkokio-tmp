package org.likelion.kkokio.domain.ordersMenu.entity;

import jakarta.persistence.*;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.domain.ordersMenu.entity.id.OrderMenuId;
import org.springframework.data.domain.Persistable;

import java.awt.*;

@Entity
public class OrdersMenu implements Persistable<OrderMenuId> {
    @EmbeddedId
    private OrderMenuId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("order_id")
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menu_id")
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private int amount;

    @Transient
    private boolean isNew = true;

    @Override
    public OrderMenuId getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        isNew = false;
    }
}
