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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private int roomNumber;
    private String roomType;
    @NotNull
    @Column(nullable = false)
    private RoomStatus status;
    @JoinColumn
    @ManyToOne
    private Owner owner;

}
