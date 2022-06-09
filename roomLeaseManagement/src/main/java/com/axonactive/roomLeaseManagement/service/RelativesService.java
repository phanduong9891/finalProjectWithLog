package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.entity.Relatives;

import java.util.List;
import java.util.Optional;

public interface RelativesService {
    List<Relatives> getAll();
    Relatives save(Relatives relatives);
    Optional<Relatives> findById(Integer id);
    void deleteById(Integer id);
}
