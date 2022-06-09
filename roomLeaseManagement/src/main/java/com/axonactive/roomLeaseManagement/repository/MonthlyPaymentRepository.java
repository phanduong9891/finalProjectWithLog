package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment,Integer> {
}
