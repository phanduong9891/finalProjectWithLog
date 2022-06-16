package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.service.MonthlyPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MonthlyPaymentServiceImplTest {
@Autowired
    MonthlyPaymentService monthlyPaymentService;
    @Test
    void numberOfMonthlyPayment() {
        System.out.println(monthlyPaymentService.numberOfMonthlyPayment(LocalDate.of(2022,5,1), LocalDate.of(2022,7,1)));
    }
}