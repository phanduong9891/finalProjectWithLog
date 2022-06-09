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
