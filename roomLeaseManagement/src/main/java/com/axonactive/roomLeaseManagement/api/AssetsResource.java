package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Assets;
import com.axonactive.roomLeaseManagement.entity.Room;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.AssetsRequest;
import com.axonactive.roomLeaseManagement.service.Impl.AssetsServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.RoomServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.AssetsDto;
import com.axonactive.roomLeaseManagement.service.mapper.AssetsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
@RestController
@RequestMapping(AssetsResource.PATH)
public class AssetsResource {
    public static final String PATH = "/api/assets";
    @Autowired
    private AssetsServiceImpl assetsService;
    @Autowired
    private RoomServiceImpl roomService;

    @GetMapping
    public ResponseEntity<List<AssetsDto>> getAll() {
        List<Assets> assetsList = assetsService.getAll();
        return ResponseEntity.ok(AssetsMapper.INSTANCE.toDtos(assetsList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetsDto> getById(@PathVariable(value = "id") Integer id){
        log.info("find asset by id {} ",id );
        Assets assets = assetsService.findById(id)
                .orElseThrow(ExceptionList::assetNotFound);
        return ResponseEntity.ok().body(AssetsMapper.INSTANCE.toDto(assets));
    }

    @PostMapping
    public ResponseEntity<AssetsDto> create(@RequestBody AssetsRequest assetsRequest) {
        Assets createAsset = assetsService.save(assetsService.create(assetsRequest));

        return ResponseEntity.created(URI.create((AssetsResource.PATH + "/" + createAsset.getId()))).body(AssetsMapper.INSTANCE.toDto(createAsset));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {
        log.info("find asset by id {} ",id );
        Assets assets = assetsService.findById(id)
                .orElseThrow(ExceptionList::assetNotFound);
        assetsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetsDto> update(@PathVariable(value = "id") Integer assetId, @RequestBody AssetsRequest assetsRequest) throws ResourceNotFoundException {


        Assets assetsUpdate = assetsService.save(assetsService.edit(assetId,assetsRequest));

        return ResponseEntity.ok(AssetsMapper.INSTANCE.toDto(assetsUpdate));
    }


}
