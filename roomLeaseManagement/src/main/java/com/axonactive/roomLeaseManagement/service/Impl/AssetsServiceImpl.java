package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Assets;
import com.axonactive.roomLeaseManagement.repository.AssetsRepository;
import com.axonactive.roomLeaseManagement.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {
    @Autowired
    private final AssetsRepository assetsRepository;

    @Override
    public List<Assets> getAll() {
        return assetsRepository.findAll();
    }

    @Override
    public Assets save(Assets assets) {
        return assetsRepository.save(assets);
    }

    @Override
    public Optional<Assets> findById(Integer id) {
        return assetsRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        assetsRepository.deleteById(id);

    }


}
