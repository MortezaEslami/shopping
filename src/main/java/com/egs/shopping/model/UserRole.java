package com.egs.shopping.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user_role")
@Getter
@Setter
@NoArgsConstructor
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_ROLE_ID", nullable = false, insertable = false, updatable = false)
    private Role role;

    @Column(name = "N_ROLE_ID", nullable = false)
    private Long roleId;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_USER_ID", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "N_USER_ID", nullable = false)
    private Long userId;
}
