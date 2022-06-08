package com.axonactive.roomLeaseManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Column(nullable = false)
    private String relationship;
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;
    @NotNull
    @Column(nullable = false)
    private String idCardNumber;
}
