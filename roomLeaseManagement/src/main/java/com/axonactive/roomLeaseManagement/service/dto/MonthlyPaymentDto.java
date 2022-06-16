package com.axonactive.roomLeaseManagement.service.dto;

import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyPaymentDto {
    private String time;
    private double electricityBill;
    private double waterBill;
    private double rent;
    private boolean status;
    private LocalDate paidDay;
    private PaymentMethod paymentMethod;
    private String tenantFirstName;
    private String tenantLastName;

}
