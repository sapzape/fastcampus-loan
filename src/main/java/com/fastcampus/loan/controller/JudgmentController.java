package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDTO.GrantAmount;
import com.fastcampus.loan.dto.JudgmentDTO.Request;
import com.fastcampus.loan.dto.JudgmentDTO.Response;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController {

  private final JudgmentService judgmentService;

  @PostMapping
  public ResponseDTO<Response> create(@RequestBody Request request) {
    return ok(judgmentService.create(request));
  }

  @GetMapping("/{judgmentId}")
  public ResponseDTO<Response> get(@PathVariable Long judgmentId) {
    return ok(judgmentService.get(judgmentId));
  }

  @PutMapping("/{judgmentId}")
  public ResponseDTO<Response> update(@PathVariable Long judgmentId, @RequestBody Request request) {
    return ok(judgmentService.update(judgmentId, request));
  }

  @DeleteMapping("/{judgmentId}")
  public ResponseDTO<Void> delete(@PathVariable Long judgmentId) {
    judgmentService.delete(judgmentId);
    return ok();
  }

  @PatchMapping("/{judgmentId}/grants")
  public ResponseDTO<GrantAmount> grant(@PathVariable Long judgmentId) {
    return ok(judgmentService.grant(judgmentId));
  }
}
