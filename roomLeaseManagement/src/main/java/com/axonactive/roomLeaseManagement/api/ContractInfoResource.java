package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.ContractInfo;
import com.axonactive.roomLeaseManagement.request.ContractInfoRequest;
import com.axonactive.roomLeaseManagement.service.ContractInfoService;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.RoomServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.ContractInfoDto;
import com.axonactive.roomLeaseManagement.service.mapper.ContractInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ContractInfoResource.PATH)
public class ContractInfoResource {
    public static final String PATH = "/api/contractInfo";
    @Autowired
    private ContractInfoService contractInfoService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private TenantServiceImpl tenantService;

    @GetMapping
    public ResponseEntity<List<ContractInfoDto>> getAll() {
        List<ContractInfo> contractInfoList = contractInfoService.getAll();
        return ResponseEntity.ok(ContractInfoMapper.INSTANCE.toDtos(contractInfoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractInfoDto> getById(@PathVariable(value = "id") Integer id) {
        ContractInfo contractInfo = contractInfoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
        return ResponseEntity.ok(ContractInfoMapper.INSTANCE.toDto(contractInfo));
    }

    @PostMapping
    public ResponseEntity<ContractInfoDto> create(@RequestBody ContractInfoRequest contractInfoRequest) throws ResourceNotFoundException {
        ContractInfo createContractInfo = new ContractInfo(
                null,
                contractInfoRequest.getRent(),
                contractInfoRequest.getDeposit(),
                contractInfoRequest.getElectricityPrice(),
                contractInfoRequest.getWaterPrice(),
                contractInfoRequest.getMaximumGuest(),
                contractInfoRequest.getType(),
                roomService.findById(contractInfoRequest.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room Not found")),
                contractService.findById(contractInfoRequest.getContractId()).orElseThrow(() -> new ResourceNotFoundException("Contract not found"))
        );

        contractInfoService.save(createContractInfo);

        return ResponseEntity.created(URI.create((ContractResource.PATH + "/" + createContractInfo.getId()))).body(ContractInfoMapper.INSTANCE.toDto(createContractInfo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractInfoDto> update(@PathVariable(value = "id") Integer id, @RequestBody ContractInfoRequest contractInfoRequest) throws ResourceNotFoundException {
        ContractInfo editContractInfo = contractInfoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        editContractInfo.setRent(contractInfoRequest.getRent());
        editContractInfo.setDeposit(contractInfoRequest.getDeposit());
        editContractInfo.setElectricityPrice(contractInfoRequest.getElectricityPrice());
        editContractInfo.setWaterPrice(contractInfoRequest.getWaterPrice());
        editContractInfo.setMaximumGuest(contractInfoRequest.getMaximumGuest());
        editContractInfo.setType(contractInfoRequest.getType());
        editContractInfo.setRoom(roomService.findById(contractInfoRequest.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room not found")));
        editContractInfo.setContract(contractService.findById(contractInfoRequest.getContractId()).orElseThrow(() -> new ResourceNotFoundException("Contrac not found")));

        ContractInfo contractInfoUpdate = contractInfoService.save(editContractInfo);

        return ResponseEntity.ok(ContractInfoMapper.INSTANCE.toDto(contractInfoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        ContractInfo contractInfo = contractInfoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractInfo not found"));
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
