package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.repository.MonthlyServiceUsingRepository;
import com.axonactive.roomLeaseManagement.service.MonthlyServiceUsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyServiceUsingServiceImpl implements MonthlyServiceUsingService {
    @Autowired
    private final MonthlyServiceUsingRepository monthlyServiceUsingRepository;

    @Override
    public List<MonthlyServiceUsing> getAll() {
        return monthlyServiceUsingRepository.findAll();
    }

    @Override
    public MonthlyServiceUsing save(MonthlyServiceUsing monthlyServiceUsing) {
        return monthlyServiceUsingRepository.save(monthlyServiceUsing);
    }

    @Override
    public Optional<MonthlyServiceUsing> findById(Integer id) {
        return monthlyServiceUsingRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        monthlyServiceUsingRepository.deleteById(id);
    }
}
