package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;

import java.util.List;
import java.util.Optional;

public interface MonthlyServiceUsingService {
    List<MonthlyServiceUsing> getAll();
    MonthlyServiceUsing save(MonthlyServiceUsing monthlyServiceUsing);
    Optional<MonthlyServiceUsing> findById(Integer id);
    void deleteById(Integer id);
}
