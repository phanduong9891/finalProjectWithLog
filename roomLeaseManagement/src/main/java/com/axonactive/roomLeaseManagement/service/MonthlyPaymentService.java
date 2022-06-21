package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthlyPaymentService {
    List<MonthlyPayment> getAll();
    MonthlyPayment save(MonthlyPayment monthlyPayment);
    Optional<MonthlyPayment> findById(Integer id);
    void deleteById(Integer id);
    List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2);
    Integer numberOfMonthlyPayment(Month month, String year);
    double totalElectricityBill(LocalDate date1, LocalDate date2);
    double totalWaterBill(LocalDate date1, LocalDate date2);
    double totalRent(LocalDate date1, LocalDate date2);
    double totalRevenue(LocalDate date1, LocalDate date2);
    List<MonthlyPayment> findByPaid(Boolean status);
    Integer numberOfPayThroughMethod(LocalDate date1, LocalDate date2, PaymentMethod paymentMethod);

}
