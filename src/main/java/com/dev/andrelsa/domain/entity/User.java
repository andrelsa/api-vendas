package com.dev.andrelsa.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SequenceGenerator(initialValue = 2, name = "user_seq", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_birth")
    private LocalDate localDate;

    @Column
    private boolean admin;

}
