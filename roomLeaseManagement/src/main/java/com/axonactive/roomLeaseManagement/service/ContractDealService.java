package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.entity.ContractType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContractDealService {
    List<ContractDeal> getAll();
    ContractDeal save(ContractDeal contractDeal);
    Optional<ContractDeal> findById(Integer id);
    void deleteById(Integer id);
    Optional<ContractDeal> findByContractTenantPhoneNumber(String phoneNumber);
    Optional<ContractDeal> findByContractId(Integer id);


}
