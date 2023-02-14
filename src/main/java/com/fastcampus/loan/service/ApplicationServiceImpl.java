package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.AcceptTerms;
import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.ApplicationDTO;
import com.fastcampus.loan.dto.ApplicationDTO.Request;
import com.fastcampus.loan.dto.ApplicationDTO.Response;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.AcceptTermsRepository;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.JudgmentRepository;
import com.fastcampus.loan.repository.TermsRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

  private final ApplicationRepository applicationRepository;

  private final TermsRepository termsRepository;

  private final AcceptTermsRepository acceptTermsRepository;

  private final JudgmentRepository judgmentRepository;

  private final ModelMapper modelMapper;

  @Override
  public Response create(Request request) {
    Application application = modelMapper.map(request, Application.class);
    application.setAppliedAt(LocalDateTime.now());

    Application applied = applicationRepository.save(application);

    return modelMapper.map(applied, Response.class);
  }

  @Override
  public Response get(Long applicationId) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    return modelMapper.map(application, Response.class);
  }

  @Override
  public Response update(Long applicationId, Request request) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    application.setName(request.getName());
    application.setCellPhone(request.getCellPhone());
    application.setEmail(request.getEmail());
    application.setHopeAmount(request.getHopeAmount());

    applicationRepository.save(application);

    return modelMapper.map(application, Response.class);
  }

  @Override
  public void delete(Long applicationId) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    application.setIsDeleted(true);

    applicationRepository.save(application);
  }

  @Override
  public Boolean acceptTerms(Long applicationId, ApplicationDTO.AcceptTerms dto) {
    applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    List<Terms> termsList = termsRepository.findAll(Sort.by(Direction.ASC, "termsId"));
    if (termsList.isEmpty()) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }

    List<Long> acceptTermsIds = dto.getAcceptTermsIds();
    if (termsList.size() != acceptTermsIds.size()) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }

    List<Long> termsIds = termsList.stream().map(Terms::getTermsId).collect(Collectors.toList());
    Collections.sort(acceptTermsIds);

    if (!termsIds.containsAll(acceptTermsIds)) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }

    for (Long termsId : acceptTermsIds) {
      AcceptTerms accepted = AcceptTerms.builder()
          .termsId(termsId)
          .applicationId(applicationId)
          .build();

      acceptTermsRepository.save(accepted);
    }

    return true;
  }

  @Transactional
  @Override
  public Response contract(Long applicationId) {
    Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    judgmentRepository.findByApplicationId(applicationId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    if (application.getApprovalAmount() == null
        || application.getApprovalAmount().compareTo(BigDecimal.ZERO) == 0) {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    }

    application.setContractedAt(LocalDateTime.now());

    Application updated = applicationRepository.save(application);

    return modelMapper.map(updated, Response.class);
  }
}
