package org.likelion.kkokio.domain.extra.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.likelion.kkokio.global.base.entity.BaseEntity;

@Getter
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "extra_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "extra_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "extra_delete_at"))
})
public class Extra extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extraId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String extraName;

    @Column(nullable = false)
    private Long extraPrice;

    public void createInfo(Menu menu) {
        this.menu = menu;
    }
}
