package org.likelion.kkokio.domain.ordersMenu.entity.id;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuId implements Serializable {
    private Long order_id;
    private Long menu_id;
}
