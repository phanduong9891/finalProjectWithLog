package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.repository.TenantRepository;
import com.axonactive.roomLeaseManagement.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    @Autowired
    private final TenantRepository tenantRepository;

    @Override
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public Optional<Tenant> findById(Integer id) {
        return tenantRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        tenantRepository.deleteById(id);

    }

    @Override
    public Optional<Tenant> findByPhoneNumber(String phoneNumber) {
        return tenantRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Tenant> findByIdCardNumber(String idCardNumber) {
        return tenantRepository.findByIdCardNumber(idCardNumber);
    }
}