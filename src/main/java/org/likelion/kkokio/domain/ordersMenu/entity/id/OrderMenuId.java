package org.likelion.kkokio.domain.ordersMenu.entity.id;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OrderMenuId implements Serializable {
    private Long order_id;
    private Long menu_id;
}
