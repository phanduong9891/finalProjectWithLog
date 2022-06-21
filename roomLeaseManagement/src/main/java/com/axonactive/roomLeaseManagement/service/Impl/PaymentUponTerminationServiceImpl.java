package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.repository.PaymentUponTerminationRepository;
import com.axonactive.roomLeaseManagement.service.PaymentUponTerminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentUponTerminationServiceImpl implements PaymentUponTerminationService {
    @Autowired
    private final PaymentUponTerminationRepository paymentUponTerminationRepository;

    @Override
    public List<PaymentUponTermination> getAll() {
        return paymentUponTerminationRepository.findAll();
    }

    @Override
    public PaymentUponTermination save(PaymentUponTermination paymentUponTermination) {
        return paymentUponTerminationRepository.save(paymentUponTermination);
    }

    @Override
    public Optional<PaymentUponTermination> findById(Integer id) {
        return paymentUponTerminationRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        paymentUponTerminationRepository.deleteById(id);
    }
}