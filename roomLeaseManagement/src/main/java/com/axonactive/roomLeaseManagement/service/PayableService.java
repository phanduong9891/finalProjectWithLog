package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Payable;

import java.util.List;
import java.util.Optional;

public interface PayableService {
    List<Payable> getAll();
    Payable save(Payable payable);
    Optional<Payable> findById(Integer id);
    void deleteById(Integer id);
}
