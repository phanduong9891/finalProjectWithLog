package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import com.axonactive.roomLeaseManagement.entity.ContractType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContractInfoService {
    List<ContractInfo> getAll();
    ContractInfo save(ContractInfo contractInfo);
    Optional<ContractInfo> findById(Integer id);
    void deleteById(Integer id);
}
