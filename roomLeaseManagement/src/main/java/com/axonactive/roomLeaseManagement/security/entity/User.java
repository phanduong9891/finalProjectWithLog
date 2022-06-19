package com.axonactive.roomLeaseManagement.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Enabled
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "users")
    private List<UserRoleAssignment> roles = new ArrayList<>();
}
