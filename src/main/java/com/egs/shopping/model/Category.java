package com.egs.shopping.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_category")
@Getter
@Setter
@Audited
@NoArgsConstructor
public class Category extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "C_NAME", nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "C_CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "C_COMMENT", nullable = false)
    private String comment;

    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "N_PARENT_ID", insertable = false, updatable = false)
    private Category parent;

    @Column(name = "N_PARENT_ID")
    private Long parentId;

}