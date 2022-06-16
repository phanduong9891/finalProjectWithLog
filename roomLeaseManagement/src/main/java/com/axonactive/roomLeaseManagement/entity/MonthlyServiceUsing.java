package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MonthlyServiceUsing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double electricityUsage;

    @Enumerated(EnumType.STRING)
    private Month month;

    private String year;

    @JoinColumn
    @ManyToOne
    private ContractInfo contractInfo;
}
