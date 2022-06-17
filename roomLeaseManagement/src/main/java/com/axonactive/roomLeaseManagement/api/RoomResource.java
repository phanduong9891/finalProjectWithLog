package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.request.RoomRequest;
import com.axonactive.roomLeaseManagement.service.Impl.OwnerServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.RoomServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.RoomByStatusDto;
import com.axonactive.roomLeaseManagement.service.dto.RoomDto;
import com.axonactive.roomLeaseManagement.service.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RoomResource.PATH)
public class RoomResource {
    public static final String PATH = "/api/room";
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private OwnerServiceImpl ownerService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAll() {
        List<Room> roomList = roomService.getAll();
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Room room = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found" + id));
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }
    @GetMapping("/find")
    public ResponseEntity<RoomDto> findRoom(@RequestParam(name = "roomNumber",required = false) Integer roomNumber){
        if(roomNumber == null)
            return ResponseEntity.noContent().build();
        Room room = roomService.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Cant find room number: " + roomNumber));
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }

    @GetMapping("/roomCondition")
    public RoomByStatusDto getNumberOfRoomByStatus(){
        return roomService.numberOfRoomByStatus();
    }

    @PostMapping
    public ResponseEntity<RoomDto> create(@RequestBody RoomRequest roomRequest) throws ResourceNotFoundException {
        Room createRoom = roomService.save(new Room(
                null,
                roomRequest.getRoomNumber(),
                roomRequest.getRoomType(),
                roomRequest.getRoomStatus(),
                ownerService.findById(roomRequest.getOwnerId())
                        .orElseThrow(() -> new ResourceNotFoundException("Owner not found"))
        ));
        System.out.println(createRoom);
        return ResponseEntity.created(URI.create(RoomResource.PATH + "/" + createRoom.getId())).body(RoomMapper.INSTANCE.toDto(createRoom));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable(value = "id") Integer id, @RequestBody RoomRequest roomRequest) throws ResourceNotFoundException {
        Room editRoom = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        editRoom.setRoomNumber(roomRequest.getRoomNumber());
        editRoom.setRoomType(roomRequest.getRoomType());
        editRoom.setStatus(roomRequest.getRoomStatus());
        editRoom.setOwner(ownerService.findById(roomRequest.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found")));

        Room roomUpdate = roomService.save(editRoom);

        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(roomUpdate));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Room room = roomService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
