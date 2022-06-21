package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.repository.ContractDealRepository;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractDealServiceImpl implements ContractDealService {
    @Autowired
    private final ContractDealRepository contractDealRepository;

    @Override
    public List<ContractDeal> getAll() {
        return contractDealRepository.findAll();
    }

    @Override
    public ContractDeal save(ContractDeal contractDeal) {
        return contractDealRepository.save(contractDeal);
    }

    @Override
    public Optional<ContractDeal> findById(Integer id) {
        return contractDealRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        contractDealRepository.deleteById(id);
    }

    @Override
    public Optional<ContractDeal> findByContractTenantPhoneNumber(String phoneNumber) {
        return contractDealRepository.findByContractTenantPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<ContractDeal> findByContractId(Integer id) {
        return contractDealRepository.findByContractId(id);
    }

}
