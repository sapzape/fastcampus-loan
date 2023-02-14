package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.BalanceDTO.CreateRequest;
import com.fastcampus.loan.dto.BalanceDTO.Response;
import com.fastcampus.loan.dto.BalanceDTO.UpdateRequest;

public interface BalanceService {

  Response create(Long applicationId, CreateRequest request);

  Response get(Long applicationId);

  Response update(Long applicationId, UpdateRequest request);

  void delete(Long applicationId);
}
