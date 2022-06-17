package com.axonactive.roomLeaseManagement.service.Impl;

import com.axonactive.roomLeaseManagement.entity.Month;
import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import com.axonactive.roomLeaseManagement.entity.PaymentMethod;
import com.axonactive.roomLeaseManagement.repository.MonthlyPaymentRepository;
import com.axonactive.roomLeaseManagement.service.MonthlyPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyPaymentServiceImpl implements MonthlyPaymentService {
    @Autowired
    private final MonthlyPaymentRepository monthlyPaymentRepository;

    @Override
    public List<MonthlyPayment> getAll() {
        return monthlyPaymentRepository.findAll();
    }

    @Override
    public MonthlyPayment save(MonthlyPayment monthlyPayment) {
        return monthlyPaymentRepository.save(monthlyPayment);
    }

    @Override
    public Optional<MonthlyPayment> findById(Integer id) {
        return monthlyPaymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        monthlyPaymentRepository.deleteById(id);
    }

    @Override
    public List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.findByPaidDayBetween(date1,date2);
    }

    @Override
    public Integer numberOfMonthlyPayment(Month month, String year) {
        return monthlyPaymentRepository.numberOfMonthlyPayment(month,year);
    }

    @Override
    public double totalElectricityBill(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalElectricityBill(date1,date2);
    }

    @Override
    public double totalWaterBill(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalWaterBill(date1,date2);
    }

    @Override
    public double totalRent(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalRent(date1,date2);
    }

    @Override
    public double totalRevenue(LocalDate date1, LocalDate date2) {
        return monthlyPaymentRepository.totalRevenue(date1,date2);
    }

    @Override
    public List<MonthlyPayment> findByStatus(Boolean status) {
        return monthlyPaymentRepository.findByStatus(status);
    }

    @Override
    public Integer numberOfPayThroughMethod(LocalDate date1, LocalDate date2, PaymentMethod paymentMethod) {
        return monthlyPaymentRepository.numberOfPayThroughMethod(date1,date2,paymentMethod);
    }


}
