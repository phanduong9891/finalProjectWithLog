package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Owner;
import com.axonactive.roomLeaseManagement.repository.OwnerRepository;
import com.axonactive.roomLeaseManagement.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> findById(Integer id) {
        return ownerRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        ownerRepository.deleteById(id);
    }
}