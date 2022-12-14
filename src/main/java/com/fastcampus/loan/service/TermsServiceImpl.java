package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.TermsDTO.Request;
import com.fastcampus.loan.dto.TermsDTO.Response;
import com.fastcampus.loan.repository.TermsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

  private final TermsRepository termsRepository;

  private final ModelMapper modelMapper;

  @Override
  public Response create(Request request) {
    Terms terms = modelMapper.map(request, Terms.class);
    Terms created = termsRepository.save(terms);

    return modelMapper.map(created, Response.class);
  }

  @Override
  public List<Response> getAll() {
    List<Terms> termsList = termsRepository.findAll();

    return termsList.stream().map(t -> modelMapper.map(t, Response.class)).collect(Collectors.toList());
  }
}
