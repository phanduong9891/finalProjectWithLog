package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.MonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyPaymentServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.BusinessReportDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyPaymentDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//chua test lai
@RestController
@RequestMapping(MonthlyPaymentResource.PATH)
public class MonthlyPaymentResource {
    public static final String PATH = "/api/monthlyPayment";
    @Autowired
    private MonthlyPaymentServiceImpl monthlyPaymentService;
    @Autowired
    private ContractServiceImpl contractService;

    @GetMapping
    public ResponseEntity<List<MonthlyPaymentDto>> getAll(@RequestParam(name = "date1",required = false) String date1, @RequestParam(name = "date2",required = false)String date2) {
        if (null == date1 && null == date2){List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.getAll();
        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));}
        else{
            List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.findByPaidDayBetween(LocalDate.parse(date1),LocalDate.parse(date2));
            return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
        return ResponseEntity.ok().body(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPayment));
    }

    @GetMapping("/showReport")
    public ResponseEntity<BusinessReportDto> showReport(@RequestParam(name = "date1")String date1, @RequestParam(name = "date2")String date2){
        return ResponseEntity.ok(new BusinessReportDto(
                monthlyPaymentService.totalElectricityBill(LocalDate.parse(date1),LocalDate.parse(date2)),
                monthlyPaymentService.totalWaterBill(LocalDate.parse(date1),LocalDate.parse(date2)),
                monthlyPaymentService.totalRent(LocalDate.parse(date1),LocalDate.parse(date2)),
                monthlyPaymentService.totalRevenue(LocalDate.parse(date1),LocalDate.parse(date2)),
                monthlyPaymentService.numberOfPayThroughMethod(LocalDate.parse(date1),LocalDate.parse(date2), PaymentMethod.CASH),
                monthlyPaymentService.numberOfPayThroughMethod(LocalDate.parse(date1),LocalDate.parse(date2),PaymentMethod.CARD))
        );
    }

    @GetMapping("/Unpaid")
    public ResponseEntity <List<MonthlyPaymentDto>> getUnpaid(){
        List<MonthlyPayment> monthlyPayment = monthlyPaymentService.findByStatus(false);
        if(monthlyPayment.size() ==0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPayment));
    }


    @PostMapping
    public ResponseEntity<MonthlyPaymentDto> create(@RequestBody MonthlyPaymentRequest monthlyPaymentRequest) throws ResourceNotFoundException {
        MonthlyPayment createMonthlyPayment = monthlyPaymentService.save(
                new MonthlyPayment(null,
                        monthlyPaymentRequest.getMonth(),
                        monthlyPaymentRequest.getYear(),
                        monthlyPaymentRequest.getElectricityBill(),
                        monthlyPaymentRequest.getWaterBill(),
                        monthlyPaymentRequest.getRent(),
                        monthlyPaymentRequest.isStatus(),
                        monthlyPaymentRequest.getPaidDay(),
                        monthlyPaymentRequest.getPaymentMethod(),
                        contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(()-> new ResourceNotFoundException("Contract not found")))
        );
        return ResponseEntity.created(URI.create((MonthlyPaymentResource.PATH + "/" + createMonthlyPayment.getId()))).body(MonthlyPaymentMapper.INSTANCE.toDto(createMonthlyPayment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MonthlyPayment not found"));
        monthlyPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> update(@PathVariable(value = "id") Integer id, @RequestBody MonthlyPaymentRequest monthlyPaymentRequest) throws ResourceNotFoundException {
        MonthlyPayment editMonthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MonthlyPayment not found"));
        editMonthlyPayment.setMonth(monthlyPaymentRequest.getMonth());
        editMonthlyPayment.setYear(monthlyPaymentRequest.getYear());
        editMonthlyPayment.setElectricityBill(monthlyPaymentRequest.getElectricityBill());
        editMonthlyPayment.setWaterBill(monthlyPaymentRequest.getWaterBill());
        editMonthlyPayment.setRent(monthlyPaymentRequest.getRent());
        editMonthlyPayment.setContract(contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(()-> new ResourceNotFoundException("Contract not found")));

        MonthlyPayment monthlyPaymentUpdate = monthlyPaymentService.save(editMonthlyPayment);


        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPaymentUpdate));
    }
}