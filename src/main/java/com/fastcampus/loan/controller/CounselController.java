package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.CounselService;
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
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

  private final CounselService counselService;

  @PostMapping
  public ResponseDTO<Response> create(@RequestBody Request request) {
    return ok(counselService.create(request));
  }

  @GetMapping("/{counselId}")
  public ResponseDTO<Response> get(@PathVariable Long counselId) {
    return ok(counselService.get(counselId));
  }

  @PutMapping("/{counselId}")
  public ResponseDTO<Response> update(@PathVariable Long counselId, @RequestBody Request request) {
    return ok(counselService.update(counselId, request));
  }

  @DeleteMapping("/{counselId}")
  public ResponseDTO<Void> delete(@PathVariable Long counselId) {
    counselService.delete(counselId);
    return ok();
  }
}
