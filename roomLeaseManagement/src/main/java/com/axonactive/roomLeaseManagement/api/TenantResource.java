package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Tenant;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.TenantRequest;
import com.axonactive.roomLeaseManagement.service.Impl.RelativesServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.TenantDto;
import com.axonactive.roomLeaseManagement.service.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TenantResource.PATH)
public class TenantResource {
    public static final String PATH = "/api/tenant";
    @Autowired
    private TenantServiceImpl tenantService;
    @Autowired
    private RelativesServiceImpl relativesService;

    @GetMapping
    public ResponseEntity<List<TenantDto>> getAll() {
        List<Tenant> tenantList = tenantService.getAll();
        return ResponseEntity.ok(TenantMapper.INSTANCE.toDtos(tenantList));
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<TenantDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
//        Tenant tenant = tenantService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
//        return ResponseEntity.ok().body(TenantMapper.INSTANCE.toDto(tenant));
//    }
    @GetMapping("/search")
    public ResponseEntity<TenantDto> searchBy(@RequestParam(name = "phoneNumber",required = false)String phoneNumber, @RequestParam(name = "idCardNumber",required = false)String idCardNumber ) throws ResourceNotFoundException {
        if(null == idCardNumber){
        Tenant tenant = tenantService.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with phone number: " + phoneNumber));
        return ResponseEntity.ok().body(TenantMapper.INSTANCE.toDto(tenant));}
        Tenant tenant = tenantService.findByIdCardNumber(idCardNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found with id card number: " + idCardNumber));
        return ResponseEntity.ok().body(TenantMapper.INSTANCE.toDto(tenant));
    }

    @PostMapping
    public ResponseEntity<TenantDto> create(@RequestBody TenantRequest tenantRequest) throws ResourceNotFoundException {
        Tenant createTenant = tenantService.save(
                new Tenant(null,
                        tenantRequest.getFirstName(),
                        tenantRequest.getLastName(),
                        tenantRequest.getGender(),
                        tenantRequest.getPhoneNumber(),
                        tenantRequest.getIdCardNumber(),
                        tenantRequest.getBirthday(),
                        relativesService.findById(tenantRequest.getRelativesId())
                                .orElseThrow(() -> new ResourceNotFoundException("Relative not found")))

        );
        return ResponseEntity.created(URI.create((TenantResource.PATH + "/" + createTenant.getId()))).body(TenantMapper.INSTANCE.toDto(createTenant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Tenant tenant = tenantService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        tenantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantDto> update(@PathVariable(value = "id") Integer id, @RequestBody TenantRequest tenantRequest) throws ResourceNotFoundException {
        Tenant editTenant = tenantService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        editTenant.setFirstName(tenantRequest.getFirstName());
        editTenant.setLastName(tenantRequest.getLastName());
        editTenant.setGender(tenantRequest.getGender());
        editTenant.setPhoneNumber(tenantRequest.getPhoneNumber());
        editTenant.setIdCardNumber(tenantRequest.getIdCardNumber());
        editTenant.setBirthday(tenantRequest.getBirthday());
        editTenant.setRelatives(relativesService.findById(tenantRequest.getRelativesId()).orElseThrow(() -> new org.springframework.data.rest.webmvc.ResourceNotFoundException("Relative not found")));

        Tenant tenantUpdate = tenantService.save(editTenant);

        return ResponseEntity.ok(TenantMapper.INSTANCE.toDto(tenantUpdate));
    }


}
