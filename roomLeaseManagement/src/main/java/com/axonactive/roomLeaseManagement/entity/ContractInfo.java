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
public class ContractInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private double rent;
    @NotNull
    @Column(nullable = false)
    private double deposit;
    @NotNull
    @Column(nullable = false, name = "electricity_price_per_Kw")
    private double electricityPrice;
    @NotNull
    @Column(nullable = false)
    private double waterPrice;
    @NotNull
    private int maximumTenant;

    @JoinColumn
    @ManyToOne
    private Room room;

    @JoinColumn
    @ManyToOne
    private Contract contract;

}
