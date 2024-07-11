package org.likelion.kkokio.domain.adminAccount.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.likelion.kkokio.global.base.entity.BaseEntity;

@Getter
@Entity
@Table(name = "admin_account")
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "account_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "account_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "account_delete_at"))
})
public class AdminAccount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    @Size(max = 50)
    private String accountLoginId;

    @Column(nullable = false)
    @Size(max = 254)
    private String accountLoginPassword;

    @Column(nullable = false)
    @Size(max = 50)
    private String accountName;
}
