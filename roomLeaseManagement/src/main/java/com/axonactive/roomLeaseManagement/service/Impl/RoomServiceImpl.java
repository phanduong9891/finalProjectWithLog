package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.repository.RoomRepository;
import com.axonactive.roomLeaseManagement.request.RoomRequest;
import com.axonactive.roomLeaseManagement.service.RoomService;
import com.axonactive.roomLeaseManagement.service.dto.RoomByStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private OwnerServiceImpl ownerService;

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

    @Override
    public RoomByStatusDto numberOfRoomByStatus() {
        return new RoomByStatusDto(
                roomRepository.findByStatus(RoomStatus.RENTED).size(),
                roomRepository.findByStatus(RoomStatus.AVAILABLE).size(),
                roomRepository.findByStatus(RoomStatus.UNAVAILABLE).size());
    }

    @Override
    public Optional<Room> findByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> showRoomByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status);
    }

    @Override
    public Room create(RoomRequest roomRequest) {
        if(!ownerService.findById(roomRequest.getOwnerId()).isPresent()){
            log.info("Cant find owner of this room with this id {} ", roomRequest.getOwnerId());
            throw ExceptionList.ownerNotFound();
        }
        Room room = new Room();

        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomType(roomRequest.getRoomType());
        room.setStatus(roomRequest.getRoomStatus());
        room.setOwner(ownerService.findById(roomRequest.getOwnerId()).get());

        return room;
    }

    @Override
    public Room edit(Integer roomId, RoomRequest roomRequest) {

        if(!roomRepository.findById(roomId).isPresent()){
            log.info("cant find room with this id {} ", roomId);
            throw ExceptionList.roomNotFound();
        }

        if(!ownerService.findById(roomRequest.getOwnerId()).isPresent()){
            log.info("Cant find owner of this room with this id {} ", roomRequest.getOwnerId());
            throw ExceptionList.ownerNotFound();
        }

        Room room = roomRepository.findById(roomId).get();

        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomType(roomRequest.getRoomType());
        room.setStatus(roomRequest.getRoomStatus());
        room.setOwner(ownerService.findById(roomRequest.getOwnerId()).get());

        return room;
    }
}