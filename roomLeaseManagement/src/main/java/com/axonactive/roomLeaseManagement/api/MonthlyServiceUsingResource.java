package com.axonactive.roomLeaseManagement.api;



import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.exception.ResourceNotFoundException;
import com.axonactive.roomLeaseManagement.request.MonthlyServiceUsingRequest;
import com.axonactive.roomLeaseManagement.service.ContractInfoService;
import com.axonactive.roomLeaseManagement.service.Impl.MonthlyServiceUsingServiceImpl;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyServiceUsingDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyServiceUsingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MonthlyServiceUsingResource.PATH)
public class MonthlyServiceUsingResource {
    public static final String PATH = "/api/monthlyServiceUsing";
    @Autowired
    private MonthlyServiceUsingServiceImpl monthlyServiceUsingService;
    @Autowired
    private ContractInfoService contractInfoService;

    @GetMapping
    public ResponseEntity<List<MonthlyServiceUsingDto>> getAll() {
        List<MonthlyServiceUsing> monthlyServiceUsingList = monthlyServiceUsingService.getAll();
        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toDtos(monthlyServiceUsingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyServiceUsingDto> getById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyServiceUsing monthlyServiceUsing = monthlyServiceUsingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id Not found: " + id));
        return ResponseEntity.ok().body(MonthlyServiceUsingMapper.INSTANCE.toDto(monthlyServiceUsing));
    }

    @PostMapping
    public ResponseEntity<MonthlyServiceUsingDto> create(@RequestBody MonthlyServiceUsingRequest monthlyServiceUsingRequest) throws ResourceNotFoundException {
        MonthlyServiceUsing createMonthlyServiceUsing = monthlyServiceUsingService.save(
                new MonthlyServiceUsing(null,
                        monthlyServiceUsingRequest.getElectricityUsage(),
                        monthlyServiceUsingRequest.getYearMonth(),
                        contractInfoService.findById(monthlyServiceUsingRequest.getContractInfoId()).orElseThrow(()-> new ResourceNotFoundException("ContractInfo not found")))
        );
        return ResponseEntity.created(URI.create((MonthlyServiceUsingResource.PATH + "/" + createMonthlyServiceUsing.getId()))).body(MonthlyServiceUsingMapper.INSTANCE.toDto(createMonthlyServiceUsing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        MonthlyServiceUsing monthlyServiceUsing = monthlyServiceUsingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MonthlyServiceUsing not found"));
        monthlyServiceUsingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonthlyServiceUsingDto> update(@PathVariable(value = "id") Integer id, @RequestBody MonthlyServiceUsingRequest monthlyServiceUsingRequest) throws ResourceNotFoundException {
        MonthlyServiceUsing editMonthlyServiceUsing = monthlyServiceUsingService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MonthlyServiceUsing not found"));
        editMonthlyServiceUsing.setElectricityUsage(monthlyServiceUsingRequest.getElectricityUsage());
        editMonthlyServiceUsing.setYearMonth(monthlyServiceUsingRequest.getYearMonth());

        MonthlyServiceUsing monthlyServiceUsingUpdate = monthlyServiceUsingService.save(editMonthlyServiceUsing);

        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toDto(monthlyServiceUsingUpdate));
    }
}