package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.ContractRequest;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.ContractDto;

import com.axonactive.roomLeaseManagement.service.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<ContractDto> getById(@PathVariable(value = "id") Integer id, @RequestParam(name = "phoneNumber", required = false) String phoneNumber) throws ResourceNotFoundException {
        if (null == phoneNumber) {
            return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractService.findById(id)
                    .orElseThrow(ExceptionList::contractNotFound)));
        }
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractService.findByTenantPhoneNumber(phoneNumber)
                .orElseThrow(ExceptionList::contractNotFound)));
    }
    @GetMapping("/aboutExpiredContract")
    public ResponseEntity<List<ContractDto>> getAboutExpiredContract(){
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractService.getContractFinishedInTwoMonths()));
    }

    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Contract createContract = contractService.save(
                new Contract(null,
                        contractRequest.getDateSigned(),
                        contractRequest.getDateExpiry(),
                        contractRequest.getContractValue(),
                        tenantService.findById(contractRequest.getTenantId())
                                .orElseThrow(ExceptionList::tenantNotFound))
        );
        return ResponseEntity.created(URI.create((ContractResource.PATH + "/" + createContract.getId()))).body(ContractMapper.INSTANCE.toDto(createContract));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Contract contract = contractService.findById(id)
                .orElseThrow(ExceptionList::contractNotFound);
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> update(@PathVariable(value = "id") Integer id, @RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Contract editContract = contractService.findById(id)
                .orElseThrow(ExceptionList::contractNotFound);
        editContract.setDateSigned(contractRequest.getDateSigned());
        editContract.setDateExpiry(contractRequest.getDateExpiry());
        editContract.setContractValue(contractRequest.getContractValue());
        editContract.setTenant(tenantService.findById(contractRequest.getTenantId())
                .orElseThrow(ExceptionList::tenantNotFound));

        Contract contractUpdate = contractService.save(editContract);


        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contractUpdate));
    }
}

