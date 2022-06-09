package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.repository.MonthlyPaymentRepository;

import java.util.List;
import java.util.Optional;

public interface MonthlyPaymentService {
    List<MonthlyPayment> getAll();
    MonthlyPayment save(MonthlyPayment monthlyPayment);
    Optional<MonthlyPayment> findById(Integer id);
    void deleteById(Integer id);
}
