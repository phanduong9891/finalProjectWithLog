package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.entity.RoomStatus;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResponseException;
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
    public ResponseEntity<RoomDto> getById(@PathVariable(value = "id") Integer id){
        Room room = roomService.findById(id)
                .orElseThrow(ExceptionList::roomNotFound);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }
    @GetMapping("/find")
    public ResponseEntity<RoomDto> findRoom(@RequestParam(name = "roomNumber",required = false) Integer roomNumber){
        if(roomNumber == null)
            return ResponseEntity.noContent().build();
        Room room = roomService.findByRoomNumber(roomNumber)
                .orElseThrow(ExceptionList::roomNotFound);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(room));
    }

    @GetMapping("/roomCondition")
    public RoomByStatusDto getNumberOfRoomByStatus(){
        return roomService.numberOfRoomByStatus();
    }

//nhap status sai thi xu sao?
    @GetMapping("/roomStatus/{status}")
    public ResponseEntity<List<RoomDto>> findRoomByStatus(@PathVariable(value = "status")RoomStatus status){
        List<Room> roomList = roomService.showRoomByStatus(status);
        return ResponseEntity.ok(RoomMapper.INSTANCE.toDtos(roomList));}


    @PostMapping
    public ResponseEntity<RoomDto> create(@RequestBody RoomRequest roomRequest){
        Room createRoom = roomService.save(new Room(
                null,
                roomRequest.getRoomNumber(),
                roomRequest.getRoomType(),
                roomRequest.getRoomStatus(),
                ownerService.findById(roomRequest.getOwnerId())
                        .orElseThrow(ExceptionList::ownerNotFound)
        ));
        System.out.println(createRoom);
        return ResponseEntity.created(URI.create(RoomResource.PATH + "/" + createRoom.getId())).body(RoomMapper.INSTANCE.toDto(createRoom));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable(value = "id") Integer id, @RequestBody RoomRequest roomRequest){
        Room editRoom = roomService.findById(id)
                .orElseThrow(ExceptionList::roomNotFound);
        editRoom.setRoomNumber(roomRequest.getRoomNumber());
        editRoom.setRoomType(roomRequest.getRoomType());
        editRoom.setStatus(roomRequest.getRoomStatus());
        editRoom.setOwner(ownerService.findById(roomRequest.getOwnerId())
                        .orElseThrow(ExceptionList::ownerNotFound));

        Room roomUpdate = roomService.save(editRoom);

        return ResponseEntity.ok(RoomMapper.INSTANCE.toDto(roomUpdate));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id){
        Room room = roomService.findById(id)
                .orElseThrow(ExceptionList::roomNotFound);
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
