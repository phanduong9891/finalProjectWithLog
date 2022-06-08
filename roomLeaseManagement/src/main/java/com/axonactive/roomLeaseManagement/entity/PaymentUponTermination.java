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
public class PaymentUponTermination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private LocalDate dayCreated;
    @Column(nullable = false)
    private double damageFee;
    @Column(nullable = false)
    private double depositRefund;

    @JoinColumn
    @ManyToOne
    private Contract contract;
}
