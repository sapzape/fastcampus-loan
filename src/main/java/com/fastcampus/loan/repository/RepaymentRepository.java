package com.fastcampus.loan.repository;

import com.fastcampus.loan.domain.Repayment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {

  List<Repayment> findAllByApplicationId(Long applicationId);
}
