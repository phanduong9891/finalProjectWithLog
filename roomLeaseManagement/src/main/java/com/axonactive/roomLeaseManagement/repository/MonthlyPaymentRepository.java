package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Integer> {
    List<MonthlyPayment> findByPaidDayBetween(LocalDate date1, LocalDate date2);


    @Query("SELECT COUNT(m.id) FROM MonthlyPayment m " +
            "WHERE m.paidDay BETWEEN ?1 AND ?2")
    Integer numberOfMonthlyPayment(LocalDate date1, LocalDate date2);
}
