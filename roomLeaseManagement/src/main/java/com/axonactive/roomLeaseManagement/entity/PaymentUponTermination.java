package com.axonactive.roomLeaseManagement.entity;

import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PreAuthorize("hasRole('ADMIN')")
public class PaymentUponTermination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private LocalDate dayCreated;//thuong de localdate.now?

    @Column
    private double damageFee;

    @Column
    private double depositRefund;//may need to check to not excel deposit set in contractDeal

    @JoinColumn
    @ManyToOne
    private Contract contract;
}
