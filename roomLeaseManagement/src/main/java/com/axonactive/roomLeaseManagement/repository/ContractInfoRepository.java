package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractInfoRepository extends JpaRepository<ContractInfo,Integer> {
}
