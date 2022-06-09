package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.repository.RoomRepository;
import com.axonactive.roomLeaseManagement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findById(Integer id) {
        return roomRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        roomRepository.deleteById(id);

    }
}