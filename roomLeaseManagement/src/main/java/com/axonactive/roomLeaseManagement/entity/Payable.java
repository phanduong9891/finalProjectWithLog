package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amountDue;

    @JoinColumn
    @ManyToOne
    private MonthlyPayment monthlyPayment;
}
