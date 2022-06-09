package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String month;
    private double electricityBill;
    @NotNull
    @Column(nullable = false)
    private double waterBill;
    @NotNull
    @Column(nullable = false)
    private double rent;

    @JoinColumn
    @ManyToOne
    private Contract contract;


}
