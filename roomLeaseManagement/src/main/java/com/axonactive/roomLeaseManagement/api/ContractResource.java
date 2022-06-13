package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.ContractRequest;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.ContractDto;

import com.axonactive.roomLeaseManagement.service.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ContractResource.PATH)
public class ContractResource {
    public static final String PATH = "/api/contract";
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private TenantServiceImpl tenantService;

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        List<Contract> contractList = contractService.getAll();
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Contract contract = contractService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        return ResponseEntity.ok().body(ContractMapper.INSTANCE.toDto(contract));
    }

    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Contract createContract = contractService.save(
                new Contract(null,
                        contractRequest.getDateSigned(),
                        contractRequest.getDateExpiry(),
                        contractRequest.getContractValue(),
                        tenantService.findById(contractRequest.getTenantId())
                                .orElseThrow(()-> new ResourceNotFoundException("Tenant not found")))
        );
        return ResponseEntity.created(URI.create((ContractResource.PATH + "/" + createContract.getId()))).body(ContractMapper.INSTANCE.toDto(createContract));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Contract contract = contractService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable(value = "id") Integer id, @RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Contract editContract = contractService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
        editContract.setDateSigned(contractRequest.getDateSigned());
        editContract.setDateExpiry(contractRequest.getDateExpiry());
        editContract.setContractValue(contractRequest.getContractValue());
        editContract.setTenant(tenantService.findById(contractRequest.getTenantId())
                .orElseThrow(()-> new ResourceNotFoundException("Tenant not found")));

        Contract contractUpdate = contractService.save(editContract);


        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractUpdate));
    }
}

