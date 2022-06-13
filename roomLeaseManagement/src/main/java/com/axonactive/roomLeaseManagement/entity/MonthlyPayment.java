package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.YearMonth;

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

    private YearMonth yearMonth;//may need to change name in the database

    private double electricityBill;//this may need to change to calculation method

    @NotNull
    @Column(nullable = false)
    private double waterBill;//this may need to check with contractInfo to make sure data integrity

    @NotNull
    @Column(nullable = false)
    private double rent;

    @NotNull
    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private LocalDate paidDay;

    private PaymentMethod paymentMethod;

    @JoinColumn
    @ManyToOne
    private Contract contract;


}
