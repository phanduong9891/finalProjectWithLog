package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.PaymentUponTerminationRequest;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.PaymentUponTerminationServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.PaymentUponTerminationDto;
import com.axonactive.roomLeaseManagement.service.mapper.PaymentUponTerminationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PaymentUponTerminationResource.PATH)
public class PaymentUponTerminationResource {
    public static final String PATH = "/api/paymentUponTermination";
    @Autowired
    private PaymentUponTerminationServiceImpl paymentUponTerminationService;
    @Autowired
    private ContractServiceImpl contractService;


    @GetMapping
    public ResponseEntity<List<PaymentUponTerminationDto>> getAll() {
        List<PaymentUponTermination> paymentUponTerminationList = paymentUponTerminationService.getAll();
        return ResponseEntity.ok(PaymentUponTerminationMapper.INSTANCE.toDtos(paymentUponTerminationList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentUponTerminationDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        PaymentUponTermination paymentUponTermination = paymentUponTerminationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        return ResponseEntity.ok().body(PaymentUponTerminationMapper.INSTANCE.toDto(paymentUponTermination));
    }

    @PostMapping
    public ResponseEntity<PaymentUponTerminationDto> create(@RequestBody PaymentUponTerminationRequest paymentUponTerminationRequest) throws ResourceNotFoundException {
        PaymentUponTermination createPaymentUponTermination = paymentUponTerminationService.save(
                new PaymentUponTermination(null,
                        paymentUponTerminationRequest.getDayCreated(),
                        paymentUponTerminationRequest.getDamageFee(),
                        paymentUponTerminationRequest.getDepositRefund(),
                        contractService.findById(paymentUponTerminationRequest.getContractId())
                                .orElseThrow(()-> new ResourceNotFoundException("Contract not found"))
                ));
        return ResponseEntity.created(URI.create((PaymentUponTerminationResource.PATH + "/" + createPaymentUponTermination.getId()))).body(PaymentUponTerminationMapper.INSTANCE.toDto(createPaymentUponTermination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        PaymentUponTermination paymentUponTermination = paymentUponTerminationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not found"));
        paymentUponTerminationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentUponTerminationDto> update(@PathVariable(value = "id") Integer id, @RequestBody PaymentUponTerminationRequest paymentUponTerminationRequest) throws ResourceNotFoundException {
        PaymentUponTermination editPaymentUponTermination = paymentUponTerminationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not found"));
        editPaymentUponTermination.setDayCreated(paymentUponTerminationRequest.getDayCreated());
        editPaymentUponTermination.setDamageFee(paymentUponTerminationRequest.getDamageFee());
        editPaymentUponTermination.setDepositRefund(paymentUponTerminationRequest.getDepositRefund());
        editPaymentUponTermination.setContract(contractService.findById(paymentUponTerminationRequest.getContractId())
                .orElseThrow(()-> new ResourceNotFoundException("Contact no found")));

        PaymentUponTermination paymentUponTerminationUpdate = paymentUponTerminationService.save(editPaymentUponTermination);

        return ResponseEntity.ok(PaymentUponTerminationMapper.INSTANCE.toDto(paymentUponTerminationUpdate));
    }
}
