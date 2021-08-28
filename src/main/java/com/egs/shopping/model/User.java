package com.egs.shopping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "c_user_name", unique = true, nullable = false)
    private String userName;


    @NotNull
    @Size(max = 100)
    @Column(name = "c_password", nullable = false)
    private String password;

    @Size(max = 100)
    @Column(name = "c_first_name", nullable = false)
    private String firstName;

    @Size(max = 100)
    @Column(name = "c_last_name", nullable = false)
    private String lastName;

    @Email
    @Size(max = 100)
    @Column(name = "c_email", unique = true)
    private String email;

    @NotNull
    @Column(name = "n_activated", nullable = false)
    private boolean activated = false;

}
