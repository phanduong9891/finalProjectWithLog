package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import com.axonactive.roomLeaseManagement.entity.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ContractInfoRepository extends JpaRepository<ContractInfo,Integer> {
    Optional<ContractInfo> findByContractTenantPhoneNumber(String phoneNumber);

}
