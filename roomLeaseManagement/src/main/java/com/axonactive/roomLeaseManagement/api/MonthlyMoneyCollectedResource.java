package com.axonactive.roomLeaseManagement.api;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import com.axonactive.roomLeaseManagement.service.MonthlyServiceUsingService;
import com.axonactive.roomLeaseManagement.service.dto.MonthlyMoneyCollectedDto;
import com.axonactive.roomLeaseManagement.service.mapper.MonthlyServiceUsingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//may be delete
@RestController
@RequestMapping(MonthlyMoneyCollectedResource.PATH)
public class MonthlyMoneyCollectedResource {
    public static final String PATH ="/api/monthlyMoneyCollected";
    @Autowired
    private MonthlyServiceUsingService monthlyServiceUsingService;

    @GetMapping
    public ResponseEntity<List<MonthlyMoneyCollectedDto>> getAll(){
        List<MonthlyServiceUsing> monthlyServiceUsingList = monthlyServiceUsingService.getAll();
        return ResponseEntity.ok(MonthlyServiceUsingMapper.INSTANCE.toMoneyCollectedDtos(monthlyServiceUsingList));
    }
}
