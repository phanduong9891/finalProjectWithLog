package com.axonactive.roomLeaseManagement.request;

import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthlyPaymentRequest {
    private YearMonth yearMonth;
    private double electricityBill;
    private double waterBill;
    private double rent;
    private boolean status;
    private LocalDate paidDay;
    private PaymentMethod paymentMethod;
    private Integer contractId;
}
