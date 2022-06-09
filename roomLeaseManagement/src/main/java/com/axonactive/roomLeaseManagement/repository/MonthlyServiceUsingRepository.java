package com.axonactive.roomLeaseManagement.repository;

import com.axonactive.roomLeaseManagement.entity.MonthlyServiceUsing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyServiceUsingRepository extends JpaRepository<MonthlyServiceUsing,Integer> {
}
