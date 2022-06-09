package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.repository.MonthlyPaymentRepository;
import com.axonactive.roomLeaseManagement.service.MonthlyPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {
    @Autowired
    private final MonthlyPaymentRepository monthlyPaymentRepository;

    @Override
    public List<MonthlyPayment> getAll() {
        return monthlyPaymentRepository.findAll();
    }

    @Override
    public MonthlyPayment save(MonthlyPayment monthlyPayment) {
        return monthlyPaymentRepository.save(monthlyPayment);
    }

    @Override
    public Optional<MonthlyPayment> findById(Integer id) {
        return monthlyPaymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        monthlyPaymentRepository.deleteById(id);
    }
}
