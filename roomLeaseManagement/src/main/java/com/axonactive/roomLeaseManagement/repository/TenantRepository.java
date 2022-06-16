package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Integer> {
    Optional<Tenant> findByPhoneNumber(String phoneNumber);
    Optional<Tenant> findByIdCardNumber(String idCardNumber);
}
