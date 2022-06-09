package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;

import java.util.List;
import java.util.Optional;

public interface PaymentUponTerminationService {
    List<PaymentUponTermination> getAll();
    PaymentUponTermination save(PaymentUponTermination paymentUponTermination);
    Optional<PaymentUponTermination> findById(Integer id);
    void deleteById(Integer id);
}
