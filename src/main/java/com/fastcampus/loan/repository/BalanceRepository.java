package com.fastcampus.loan.repository;

import com.fastcampus.loan.domain.Balance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

  Optional<Balance> findByApplicationId(Long applicationId);
}
