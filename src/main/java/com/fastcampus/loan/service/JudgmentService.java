package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.ApplicationDTO.GrantAmount;
import com.fastcampus.loan.dto.JudgmentDTO.Request;
import com.fastcampus.loan.dto.JudgmentDTO.Response;

public interface JudgmentService {

  Response create(Request request);

  Response get(Long judgmentId);

  Response getJudgmentOfApplication(Long applicationId);

  Response update(Long judgmentId, Request request);

  GrantAmount grant(Long judgmentId);

  void delete(Long judgmentId);
}
