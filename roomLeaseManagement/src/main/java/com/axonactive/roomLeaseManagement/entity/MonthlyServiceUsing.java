package com.axonactive.roomLeaseManagement.entity;

import lombok.*;

import javax.persistence.*;

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

    @JoinColumn
    @ManyToOne
    private Contract contract;
}
