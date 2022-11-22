package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {

  private final CounselRepository counselRepository;

  private final ModelMapper modelMapper;

  @Override
  public Response create(Request request) {
    Counsel counsel = modelMapper.map(request, Counsel.class);
    counsel.setAppliedAt(LocalDateTime.now());

    Counsel created = counselRepository.save(counsel);

    return modelMapper.map(created, Response.class);
  }

  @Override
  public Response get(Long counselId) {
    Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
      throw new BaseException(ResultType.SYSTEM_ERROR);
    });

    return modelMapper.map(counsel, Response.class);
  }
}
