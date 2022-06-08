package com.axonactive.roomLeaseManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false,name = "Signed_date")
    private LocalDate dateSigned;
    @NotNull
    @Column(nullable = false,name = "Expiry_date")
    private LocalDate dateExpiry;
    @NotNull
    @Column(nullable = false, name = "Value")
    private double contractValue;

    @JoinColumn
    @ManyToOne
    private Tenant tenant;
}
