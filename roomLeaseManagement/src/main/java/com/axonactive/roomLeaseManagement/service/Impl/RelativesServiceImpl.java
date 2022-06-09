package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Relatives;
import com.axonactive.roomLeaseManagement.repository.RelativesRepository;
import com.axonactive.roomLeaseManagement.service.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelativesServiceImpl implements RelativesService {
    @Autowired
    private final RelativesRepository relativesRepository;

    @Override
    public List<Relatives> getAll() {
        return relativesRepository.findAll();
    }

    @Override
    public Relatives save(Relatives relatives) {
        return relativesRepository.save(relatives);
    }

    @Override
    public Optional<Relatives> findById(Integer id) {
        return relativesRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        relativesRepository.deleteById(id);

    }
}
