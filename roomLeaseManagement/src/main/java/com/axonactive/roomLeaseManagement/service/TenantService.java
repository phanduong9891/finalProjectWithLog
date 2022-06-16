package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    List<Tenant> getAll();
    Tenant save(Tenant tenant);
    Optional<Tenant> findById(Integer id);
    void deleteById(Integer id);
    Optional<Tenant> findByPhoneNumber(String phoneNumber);
    Optional<Tenant> findByIdCardNumber(String idCardNumber);
}
