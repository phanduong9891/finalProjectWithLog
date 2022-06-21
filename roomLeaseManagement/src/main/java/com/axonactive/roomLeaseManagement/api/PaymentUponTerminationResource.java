package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.Contract;
import com.axonactive.roomLeaseManagement.entity.PaymentUponTermination;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
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
import java.util.Optional;

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
                .orElseThrow(ExceptionList::paymentUponTerminationNotFound);
        return ResponseEntity.ok().body(PaymentUponTerminationMapper.INSTANCE.toDto(paymentUponTermination));
    }

    @PostMapping
    public ResponseEntity<PaymentUponTerminationDto> create(@RequestBody PaymentUponTerminationRequest paymentUponTerminationRequest) throws ResourceNotFoundException {
       Contract contract = contractService.findById(paymentUponTerminationRequest.getContractId()).
               orElseThrow(ExceptionList::contractNotFound);

        PaymentUponTermination paymentUponTermination = new PaymentUponTermination();

        paymentUponTermination.setDayCreated( paymentUponTerminationRequest.getDayCreated());
        paymentUponTermination.setDamageFee( paymentUponTerminationRequest.getDamageFee());
        paymentUponTermination.setDepositRefund( paymentUponTerminationRequest.getDepositRefund());
        paymentUponTermination.setContract(contract);///nguyen phan set nay xuong service(if possible)

        PaymentUponTermination createPaymentUponTermination = paymentUponTerminationService.save(paymentUponTermination);

        return ResponseEntity.created(URI.create((PaymentUponTerminationResource.PATH + "/" + createPaymentUponTermination.getId()))).body(PaymentUponTerminationMapper.INSTANCE.toDto(createPaymentUponTermination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        PaymentUponTermination paymentUponTermination = paymentUponTerminationService.findById(id)
                .orElseThrow(ExceptionList::paymentUponTerminationNotFound);
        paymentUponTerminationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentUponTerminationDto> update(@PathVariable(value = "id") Integer id, @RequestBody PaymentUponTerminationRequest paymentUponTerminationRequest) throws ResourceNotFoundException {
        PaymentUponTermination editPaymentUponTermination = paymentUponTerminationService.findById(id)
                .orElseThrow(ExceptionList::paymentUponTerminationNotFound);
        editPaymentUponTermination.setDayCreated(paymentUponTerminationRequest.getDayCreated());
        editPaymentUponTermination.setDamageFee(paymentUponTerminationRequest.getDamageFee());
        editPaymentUponTermination.setDepositRefund(paymentUponTerminationRequest.getDepositRefund());
        editPaymentUponTermination.setContract(contractService.findById(paymentUponTerminationRequest.getContractId())
                .orElseThrow(ExceptionList::contractNotFound));

        PaymentUponTermination paymentUponTerminationUpdate = paymentUponTerminationService.save(editPaymentUponTermination);

        return ResponseEntity.ok(PaymentUponTerminationMapper.INSTANCE.toDto(paymentUponTerminationUpdate));
    }
}
