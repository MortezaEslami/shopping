package com.egs.shopping.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user_product_rate")
@Getter
@Setter
@NoArgsConstructor
public class UserProductRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "C_COMMENT", length = 500, nullable = false)
    private String comment;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "N_RATE", nullable = false)
    private Integer rate;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_PRODUCT_ID", nullable = false, insertable = false, updatable = false)
    private Product product;

    @Column(name = "N_PRODUCT_ID", nullable = false)
    private Long productId;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_USER_ID", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "N_USER_ID", nullable = false)
    private Long userId;

}