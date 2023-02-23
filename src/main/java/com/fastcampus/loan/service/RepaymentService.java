package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.RepaymentDTO.ListResponse;
import com.fastcampus.loan.dto.RepaymentDTO.Request;
import com.fastcampus.loan.dto.RepaymentDTO.Response;
import com.fastcampus.loan.dto.RepaymentDTO.UpdateResponse;
import java.util.List;

public interface RepaymentService {

  Response create(Long applicationId, Request request);

  List<ListResponse> get(Long applicationId);

  UpdateResponse update(Long repaymentId, Request request);

  void delete(Long repaymentId);
}
