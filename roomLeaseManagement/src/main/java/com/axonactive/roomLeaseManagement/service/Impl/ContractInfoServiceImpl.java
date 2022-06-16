package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import com.axonactive.roomLeaseManagement.repository.ContractInfoRepository;
import com.axonactive.roomLeaseManagement.service.ContractInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractInfoServiceImpl implements ContractInfoService {
    @Autowired
    private final ContractInfoRepository contractInfoRepository;

    @Override
    public List<ContractInfo> getAll() {
        return contractInfoRepository.findAll();
    }

    @Override
    public ContractInfo save(ContractInfo contractInfo) {
        return contractInfoRepository.save(contractInfo);
    }

    @Override
    public Optional<ContractInfo> findById(Integer id) {
        return contractInfoRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        contractInfoRepository.deleteById(id);
    }

}
