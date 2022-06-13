package com.axonactive.roomLeaseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyServiceUsingRequest {
    private double electricityUsage;
    private YearMonth yearMonth;
    private Integer contractInfoId;
}
