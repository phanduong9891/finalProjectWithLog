package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAll();
    Owner save(Owner owner);
    Optional<Owner> findById(Integer id);
    void deleteById(Integer id);
}
