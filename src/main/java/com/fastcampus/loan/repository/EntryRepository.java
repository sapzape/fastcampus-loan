package com.fastcampus.loan.repository;

import com.fastcampus.loan.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

  Entry findByApplicationId(Long applicationId);
}
