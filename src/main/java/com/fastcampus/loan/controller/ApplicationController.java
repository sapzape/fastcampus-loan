package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDTO.AcceptTerms;
import com.fastcampus.loan.dto.ApplicationDTO.Request;
import com.fastcampus.loan.dto.ApplicationDTO.Response;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.ApplicationService;
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
@RequestMapping("/applications")
public class ApplicationController extends AbstractController {

  private final ApplicationService applicationService;

  @PostMapping
  public ResponseDTO<Response> create(@RequestBody Request request) {
    return ok(applicationService.create(request));
  }

  @GetMapping("/{applicationId}")
  public ResponseDTO<Response> get(@PathVariable Long applicationId) {
    return ok(applicationService.get(applicationId));
  }

  @PutMapping("/{applicationId}")
  public ResponseDTO<Response> update(@PathVariable Long applicationId, @RequestBody Request request) {
    return ok(applicationService.update(applicationId, request));
  }

  @DeleteMapping("/{applicationId}")
  public ResponseDTO<Void> delete(@PathVariable Long applicationId) {
    applicationService.delete(applicationId);
    return ok();
  }

  @PostMapping("/{applicationId}/terms")
  public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody AcceptTerms request) {
    return ok(applicationService.acceptTerms(applicationId, request));
  }
}
