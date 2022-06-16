package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    private String gender;

    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @Column(nullable = false,unique = true)
    private String idCardNumber;

    private LocalDate birthday;

    @JoinColumn
    @ManyToOne
    private Relatives relatives;
}
