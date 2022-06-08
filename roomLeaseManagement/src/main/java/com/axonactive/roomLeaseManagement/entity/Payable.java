package com.axonactive.roomLeaseManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double amountDue;

    @JoinColumn
    @ManyToOne
    private MonthyPayment monthyPayment;
}
