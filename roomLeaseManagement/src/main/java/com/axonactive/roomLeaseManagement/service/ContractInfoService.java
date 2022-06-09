package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;

import java.util.List;
import java.util.Optional;

public interface ContractInfoService {
    List<ContractInfo> getAll();
    ContractInfo save(ContractInfo contractInfo);
    Optional<ContractInfo> findById(Integer id);
    void deleteById(Integer id);
}
