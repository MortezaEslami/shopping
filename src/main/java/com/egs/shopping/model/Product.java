package com.egs.shopping.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product")
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Getter
@Setter
@Audited
@NoArgsConstructor
public class Product extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "C_NAME", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "C_CODE",nullable = false)
    private String code;

    @Column(name = "C_COMMENT", length = 500)
    private String comment;

    @NotNull
    @Column(name = "N_PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "N_RATE")
    @NotAudited
    private Double rate;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Column(name = "N_CATEGORY_ID", nullable = false)
    private Long categoryId;
}
