package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.TermsDTO.Request;
import com.fastcampus.loan.dto.TermsDTO.Response;
import java.util.List;

public interface TermsService {

  Response create(Request request);

  List<Response> getAll();
}
