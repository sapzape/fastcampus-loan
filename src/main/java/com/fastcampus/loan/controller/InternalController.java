package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.EntryDTO.Request;
import com.fastcampus.loan.dto.EntryDTO.Response;
import com.fastcampus.loan.dto.EntryDTO.UpdateResponse;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.EntryService;
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
}
