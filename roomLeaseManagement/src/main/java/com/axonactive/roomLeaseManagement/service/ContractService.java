package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAll();
    Contract save(Contract contract);
    Optional<Contract> findById(Integer id);
    void deleteById(Integer id);
}
