package com.axonactive.roomLeaseManagement.api;


import com.axonactive.roomLeaseManagement.entity.*;
import com.axonactive.roomLeaseManagement.exception.ExceptionList;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.MonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.request.PaidMonthlyPaymentRequest;
import com.axonactive.roomLeaseManagement.service.ContractDealService;
import com.axonactive.roomLeaseManagement.service.Impl.ContractDealServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.ContractServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyPaymentServiceImpl;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyServiceUsingServiceImpl;
import com.axonactive.roomLeaseManagement.service.MonthlyServiceUsingService;
import com.axonactive.roomLeaseManagement.service.dto.BusinessReportDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyPaymentDto;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(MonthlyPaymentResource.PATH)
public class MonthlyPaymentResource {
    public static final String PATH = "/api/monthlyPayment";
    @Autowired
    private MonthlyPaymentServiceImpl monthlyPaymentService;
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private ContractDealServiceImpl contractDealService;
    @Autowired
    private MonthlyServiceUsingServiceImpl monthlyServiceUsingService;

    @GetMapping
    public ResponseEntity<List<MonthlyPaymentDto>> getAll(@RequestParam(name = "date1", required = false) String date1, @RequestParam(name = "date2", required = false) String date2) {
        if (null == date1 && null == date2) {
            List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.getAll();
            return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));
        } else {
            List<MonthlyPayment> monthlyPaymentList = monthlyPaymentService.findByPaidDayBetween(LocalDate.parse(date1), LocalDate.parse(date2));
            return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPaymentList));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(ExceptionList::monthlyPaymentNotFound);
        return ResponseEntity.ok().body(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPayment));
    }

    @GetMapping("/showReport")
    public ResponseEntity<BusinessReportDto> showReport(@RequestParam(name = "date1") String date1, @RequestParam(name = "date2") String date2) {
        return ResponseEntity.ok(new BusinessReportDto(
                monthlyPaymentService.totalElectricityBill(LocalDate.parse(date1), LocalDate.parse(date2)),
                monthlyPaymentService.totalWaterBill(LocalDate.parse(date1), LocalDate.parse(date2)),
                monthlyPaymentService.totalRent(LocalDate.parse(date1), LocalDate.parse(date2)),
                monthlyPaymentService.totalRevenue(LocalDate.parse(date1), LocalDate.parse(date2)),
                monthlyPaymentService.numberOfPayThroughMethod(LocalDate.parse(date1), LocalDate.parse(date2), PaymentMethod.CASH),//may need to change to calc
                monthlyPaymentService.numberOfPayThroughMethod(LocalDate.parse(date1), LocalDate.parse(date2), PaymentMethod.CARD))//may need to change to calc
        );
    }

    @GetMapping("/Unpaid")
    public ResponseEntity<List<MonthlyPaymentDto>> getUnpaid() {
        List<MonthlyPayment> monthlyPayment = monthlyPaymentService.findByPaid(false);
        if (monthlyPayment.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDtos(monthlyPayment));
    }


    @PostMapping
    public ResponseEntity<MonthlyPaymentDto> create(@RequestBody MonthlyPaymentRequest monthlyPaymentRequest) throws ResourceNotFoundException {
//        Contract contract =contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(ExceptionList::contractNotFound);
//        ContractDeal contractDeal = contractDealService.findByContractId(contract.getId()).orElseThrow(ExceptionList::contractDealNotFound);
//        MonthlyServiceUsing monthlyServiceUsing=monthlyPaymentService.
        MonthlyPayment createMonthlyPayment = new MonthlyPayment(
                null,
                monthlyPaymentRequest.getMonth(),
                monthlyPaymentRequest.getYear(),
                monthlyPaymentRequest.getElectricityBill(),
                monthlyPaymentRequest.getWaterBill(),
                monthlyPaymentRequest.getRent(),
                monthlyPaymentRequest.isPaid(),
                LocalDate.now(),
                monthlyPaymentRequest.getPaymentMethod(),
                contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(ExceptionList::contractNotFound)
        );
        return ResponseEntity.created(URI.create((MonthlyPaymentResource.PATH + "/" + createMonthlyPayment.getId()))).body(MonthlyPaymentMapper.INSTANCE.toDto(createMonthlyPayment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyPayment monthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(ExceptionList::monthlyPaymentNotFound);
        monthlyPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDto> update(@PathVariable(value = "id") Integer id, @RequestBody MonthlyPaymentRequest monthlyPaymentRequest) {
        MonthlyPayment editMonthlyPayment = monthlyPaymentService.findById(id)
                .orElseThrow(ExceptionList::monthlyPaymentNotFound);
        editMonthlyPayment.setMonth(monthlyPaymentRequest.getMonth());
        editMonthlyPayment.setYear(monthlyPaymentRequest.getYear());
        editMonthlyPayment.setElectricityBill(monthlyPaymentRequest.getElectricityBill());
        editMonthlyPayment.setWaterBill(monthlyPaymentRequest.getWaterBill());
        editMonthlyPayment.setRent(monthlyPaymentRequest.getRent());
        editMonthlyPayment.setPaidDay(monthlyPaymentRequest.getPaidDay());
        editMonthlyPayment.setContract(contractService.findById(monthlyPaymentRequest.getContractId()).orElseThrow(ExceptionList::contractNotFound));

        MonthlyPayment monthlyPaymentUpdate = monthlyPaymentService.save(editMonthlyPayment);


        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDto(monthlyPaymentUpdate));
    }

    @PutMapping("/paidAMonthlyPayment/{id}")
    public ResponseEntity<MonthlyPaymentDto> paidAMonthlyPayment(@PathVariable(value = "id") Integer id, @RequestBody PaidMonthlyPaymentRequest paidMonthlyPaymentRequest) {
        MonthlyPayment editMonthlyPayment = monthlyPaymentService.findById(id).orElseThrow(ExceptionList::monthlyPaymentNotFound);
        editMonthlyPayment.setPaid(paidMonthlyPaymentRequest.isPaid());
        editMonthlyPayment.setPaymentMethod(paidMonthlyPaymentRequest.getPaymentMethod());
        editMonthlyPayment.setPaidDay(LocalDate.now());
        MonthlyPayment paidMonthlyPayment = monthlyPaymentService.save(editMonthlyPayment);

        return ResponseEntity.ok(MonthlyPaymentMapper.INSTANCE.toDto(paidMonthlyPayment));
    }
}