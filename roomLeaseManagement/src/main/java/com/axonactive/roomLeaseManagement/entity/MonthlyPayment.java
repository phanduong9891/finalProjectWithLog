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
public class MonthlyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Enumerated(EnumType.STRING)
    private Month month;//may need to change name in the database

    private String year;

    private double electricityBill;//this may need to change to calculation method

    @NotNull
    @Column(nullable = false)
    private double waterBill;////need to check with waterPrice in contractDeal to make sure data integrity;

    @NotNull
    @Column(nullable = false)
    private double rent;//need to check with rent in contractDeal to make sure data integrity;

    @NotNull
    @Column(nullable = false, name = "paid")
    private boolean paid;

    @Column
    private LocalDate paidDay;//only have data when status is true

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @JoinColumn
    @ManyToOne
    private Contract contract;
//
//    public LocalDate getPaidDay() {
//        if (!paid){
//            return null;
//        }
//        return paidDay;
//    }
}
