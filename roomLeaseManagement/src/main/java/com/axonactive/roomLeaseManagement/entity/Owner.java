package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;
    private String email;
    private String gender;
}
