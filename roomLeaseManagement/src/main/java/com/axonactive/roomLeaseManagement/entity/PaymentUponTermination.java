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


    private LocalDate dayCreated;//thuong de localdate.now?

    @Column
    private double damageFee;

    @Column
    private double depositRefund;//may need to check to not excel deposit set in contractInfo

    @JoinColumn
    @ManyToOne
    private Contract contract;
}
