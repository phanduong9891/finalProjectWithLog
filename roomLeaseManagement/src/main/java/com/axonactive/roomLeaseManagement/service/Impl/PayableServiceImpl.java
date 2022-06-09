package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Payable;
import com.axonactive.roomLeaseManagement.repository.PayableRepository;
import com.axonactive.roomLeaseManagement.service.PayableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayableServiceImpl implements PayableService {
    @Autowired
    private final PayableRepository payableRepository;

    @Override
    public List<Payable> getAll() {
        return payableRepository.findAll();
    }

    @Override
    public Payable save(Payable payable) {
        return payableRepository.save(payable);
    }

    @Override
    public Optional<Payable> findById(Integer id) {
        return payableRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        payableRepository.deleteById(id);

    }
}