package com.axonactive.roomLeaseManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String quantity;
    private double price;
    private AssetStatus status;

    @JoinColumn
    @ManyToOne
    private Room room;
}
