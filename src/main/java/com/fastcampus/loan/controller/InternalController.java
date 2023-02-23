package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.EntryDTO.Request;
import com.fastcampus.loan.dto.EntryDTO.Response;
import com.fastcampus.loan.dto.EntryDTO.UpdateResponse;
import com.fastcampus.loan.dto.RepaymentDTO;
import com.fastcampus.loan.dto.RepaymentDTO.ListResponse;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.EntryService;
import com.fastcampus.loan.service.RepaymentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController {

  private final EntryService entryService;

  private final RepaymentService repaymentService;

  @PostMapping("{applicationId}/entries")
  public ResponseDTO<Response> create(@PathVariable Long applicationId, @RequestBody Request request) {
    return ok(entryService.create(applicationId, request));
  }

  @PutMapping("{applicationId}/entries")
  public ResponseDTO<UpdateResponse> update(@PathVariable Long applicationId, @RequestBody Request request) {
    return ok(entryService.update(applicationId, request));
  }

  @GetMapping("/entries/{entryId}")
  public ResponseDTO<Response> get(@PathVariable Long entryId) {
    return ok(entryService.get(entryId));
  }

  @DeleteMapping("/entries/{entryId}")
  public ResponseDTO<Void> delete(@PathVariable Long entryId) {
    entryService.delete(entryId);
    return ok();
  }

  @PostMapping("{applicationId}/repayments")
  public ResponseDTO<RepaymentDTO.Response> create(@PathVariable Long applicationId, @RequestBody RepaymentDTO.Request request) {
    return ok(repaymentService.create(applicationId, request));
  }

  @GetMapping("{applicationId}/repayments")
  public ResponseDTO<List<ListResponse>> getPayments(@PathVariable Long applicationId) {
    return ok(repaymentService.get(applicationId));
  }

  @PutMapping("/repayments/{repaymentId}")
  public ResponseDTO<RepaymentDTO.UpdateResponse> update(@PathVariable Long repaymentId,
      @RequestBody RepaymentDTO.Request request) {
    return ok(repaymentService.update(repaymentId, request));
  }

  @DeleteMapping("/repayments/{repaymentId}")
  public ResponseDTO<Void> deleteRepayment(@PathVariable Long repaymentId) {
    repaymentService.delete(repaymentId);
    return ok();
  }
}
