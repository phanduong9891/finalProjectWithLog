package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Assets;

import java.util.List;
import java.util.Optional;

public interface AssetsService {
    List<Assets> getAll();
    Assets save(Assets assets);
    Optional<Assets> findById(Integer id);
    void deleteById(Integer id);
}
