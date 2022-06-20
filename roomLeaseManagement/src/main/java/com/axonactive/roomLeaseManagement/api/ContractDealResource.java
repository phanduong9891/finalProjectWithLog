package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.ContractDeal;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.request.ContractDealRequest;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.RoomServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.TenantServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.ContractDealDto;
import com.axonactive.roomLeaseManagement.service.dto.TenantRoomStayDto;
import com.axonactive.roomLeaseManagement.service.mapper.ContractDealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(ContractDealResource.PATH)
public class ContractDealResource {
    public static final String PATH = "/api/contractDeal";
    @Autowired
    private ContractDealService contractDealService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private TenantServiceImpl tenantService;

    @GetMapping
    public ResponseEntity<List<ContractDealDto>> getAll(){
        List<ContractDeal> contractDeals = contractDealService.getAll();
        return ResponseEntity.ok(ContractDealMapper.INSTANCE.toDtos(contractDeals));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContractDealDto> getById(@PathVariable(value = "id") Integer id) {
        ContractDeal contractDeal = contractDealService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
        return ResponseEntity.ok(ContractDealMapper.INSTANCE.toDto(contractDeal));
    }

    @GetMapping("/getTenantAndRoom")
    public ResponseEntity<TenantRoomStayDto> getTenantAndRoom(@RequestParam(defaultValue = "empty") String phoneNumber){

        ContractDeal contractDeal = contractDealService.findByContractTenantPhoneNumber(phoneNumber)
                .orElseThrow(() -> ExceptionList.notFound("notFound","Cant find phone number: "+phoneNumber));
        return ResponseEntity.ok(ContractDealMapper.INSTANCE.toTenantAndRoomDto(contractDeal));
    }

    @PostMapping
    public ResponseEntity<ContractDealDto> create(@RequestBody ContractDealRequest contractDealRequest) throws ResourceNotFoundException {
        ContractDeal createContractDeal = new ContractDeal(
                null,
                contractDealRequest.getRent(),
                contractDealRequest.getDeposit(),
                contractDealRequest.getElectricityPrice(),
                contractDealRequest.getWaterPrice(),
                contractDealRequest.getMaximumGuest(),
                contractDealRequest.getType(),
                roomService.findById(contractDealRequest.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room Not found")),
                contractService.findById(contractDealRequest.getContractId()).orElseThrow(() -> new ResourceNotFoundException("Contract not found"))
        );

        contractDealService.save(createContractDeal);

        return ResponseEntity.created(URI.create((ContractResource.PATH + "/" + createContractDeal.getId()))).body(ContractDealMapper.INSTANCE.toDto(createContractDeal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDealDto> update(@PathVariable(value = "id") Integer id, @RequestBody ContractDealRequest contractDealRequest) throws ResourceNotFoundException {
        ContractDeal editContractDeal = contractDealService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        editContractDeal.setRent(contractDealRequest.getRent());
        editContractDeal.setDeposit(contractDealRequest.getDeposit());
        editContractDeal.setElectricityPrice(contractDealRequest.getElectricityPrice());
        editContractDeal.setWaterPrice(contractDealRequest.getWaterPrice());
        editContractDeal.setMaximumGuest(contractDealRequest.getMaximumGuest());
        editContractDeal.setType(contractDealRequest.getType());
        editContractDeal.setRoom(roomService.findById(contractDealRequest.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Room not found")));
        editContractDeal.setContract(contractService.findById(contractDealRequest.getContractId()).orElseThrow(() -> new ResourceNotFoundException("Contrac not found")));

        ContractDeal contractDealUpdate = contractDealService.save(editContractDeal);

        return ResponseEntity.ok(ContractDealMapper.INSTANCE.toDto(contractDealUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        ContractDeal contractDeal = contractDealService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContractDeal not found"));
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
