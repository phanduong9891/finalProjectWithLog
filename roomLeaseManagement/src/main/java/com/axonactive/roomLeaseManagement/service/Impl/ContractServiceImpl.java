package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.repository.ContractRepository;
import com.axonactive.roomLeaseManagement.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
@Autowired
private final ContractRepository contractRepository;
    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return contractRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);

    }

    @Override
    public Optional<Contract> findByTenantPhoneNumber(String phoneNumber) {
        return contractRepository.findByTenantPhoneNumberLike (phoneNumber);
    }

    @Override
    public List<Contract> getContractFinishedInTwoMonths() {
        List<Contract> contractList = contractRepository.findAll();
        List<Contract> contractList1 = new ArrayList<>();
        for (Contract c:contractList) {
            if(c.getDateExpiry() != null && LocalDate.now().plusMonths(2).isAfter(c.getDateExpiry()))
                contractList1.add(c);
        }
        return contractList1;
    }

}
