package com.axonactive.roomLeaseManagement.service;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthlyPaymentService {
    List<MonthlyPayment> getAll();
    MonthlyPayment save(MonthlyPayment monthlyPayment);
    Optional<MonthlyPayment> findById(Integer id);
    void deleteById(Integer id);
    List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2);
    Integer numberOfMonthlyPayment(LocalDate date1, LocalDate date2);
}
