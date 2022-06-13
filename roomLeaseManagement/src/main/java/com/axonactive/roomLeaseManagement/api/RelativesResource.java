package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Relatives;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.service.Impl.RelativesServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.RelativesDto;
import com.axonactive.roomLeaseManagement.service.mapper.RelativesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(RelativesResource.PATH)
public class RelativesResource {
    public static final String PATH = "/api/relatives";
    @Autowired
    private RelativesServiceImpl relativesService;


    @GetMapping
    public ResponseEntity<List<RelativesDto>> getAll() {
        List<Relatives> relativesList = relativesService.getAll();
        return ResponseEntity.ok(RelativesMapper.INSTANCE.toDtos(relativesList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelativesDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
        return ResponseEntity.ok().body(RelativesMapper.INSTANCE.toDto(relatives));
    }

    @PostMapping
    public ResponseEntity<RelativesDto> create(@RequestBody RelativesDto relativesDto) {
        Relatives createRelatives = relativesService.save(
                new Relatives(null,
                        relativesDto.getFirstName(),
                        relativesDto.getLastName(),
                        relativesDto.getRelationship(),
                        relativesDto.getPhoneNumber(),
                        relativesDto.getIdCardNumber()
        ));
        return ResponseEntity.created(URI.create((RelativesResource.PATH + "/" + createRelatives.getId()))).body(RelativesMapper.INSTANCE.toDto(createRelatives));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relative not found"));
        relativesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelativesDto> update(@PathVariable(value = "id") Integer id, @RequestBody RelativesDto relativesDto) throws ResourceNotFoundException {
        Relatives editRelatives = relativesService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relative not found"));
        editRelatives.setFirstName(relativesDto.getFirstName());
        editRelatives.setLastName(relativesDto.getLastName());
        editRelatives.setRelationship(relativesDto.getRelationship());
        editRelatives.setPhoneNumber(relativesDto.getPhoneNumber());
        editRelatives.setIdCardNumber(relativesDto.getIdCardNumber());

         Relatives relativesUpdate = relativesService.save(editRelatives);

        return ResponseEntity.ok(RelativesMapper.INSTANCE.toDto(relativesUpdate));
    }


}
