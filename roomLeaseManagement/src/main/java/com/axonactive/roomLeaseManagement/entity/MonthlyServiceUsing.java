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
    private YearMonth yearMonth;//may need to change column name

    @JoinColumn
    @ManyToOne
    private ContractInfo contractInfo;
}
