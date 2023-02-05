package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.domain.Judgment;
import com.fastcampus.loan.dto.ApplicationDTO.GrantAmount;
import com.fastcampus.loan.dto.JudgmentDTO.Request;
import com.fastcampus.loan.dto.JudgmentDTO.Response;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.JudgmentRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class JudgmentServiceTest {

  @InjectMocks
  private JudgmentServiceImpl judgmentService;

  @Mock
  private JudgmentRepository judgmentRepository;

  @Mock
  private ApplicationRepository applicationRepository;

  @Spy
  private ModelMapper modelMapper;

  @Test
  void Should_ReturnResponseOfNewJudgmentEntity_When_RequestNewJudgment() {
    Judgment judgmentEntity = Judgment.builder()
        .name("Member Kim")
        .applicationId(1L)
        .approvalAmount(BigDecimal.valueOf(50000000))
        .build();

    Application applicationEntity = Application.builder()
        .applicationId(1L)
        .build();

    Request request = Request.builder()
        .name("Member Kim")
        .applicationId(1L)
        .approvalAmount(BigDecimal.valueOf(50000000))
        .build();

    when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
    when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(judgmentEntity);

    Response actual = judgmentService.create(request);

    assertThat(actual.getJudgmentId()).isSameAs(judgmentEntity.getJudgmentId());
    assertThat(actual.getName()).isSameAs(judgmentEntity.getName());
    assertThat(actual.getApplicationId()).isSameAs(judgmentEntity.getApplicationId());
    assertThat(actual.getApprovalAmount()).isSameAs(judgmentEntity.getApprovalAmount());
  }

  @Test
  void Should_ReturnResponseOfExistJudgmentEntity_When_RequestExistJudgmentId() {
    Long findId = 1L;

    Judgment entity = Judgment.builder()
        .judgmentId(1L)
        .build();

    when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

    Response actual = judgmentService.get(1L);

    assertThat(actual.getJudgmentId()).isSameAs(findId);
  }

  // TODO: get judgment of application
  @Test
  void Should_ReturnResponseOfExistJudgmentEntity_When_RequestExistApplicationId() {
    Long findId = 1L;

    Judgment entity = Judgment.builder()
        .judgmentId(1L)
        .build();

    Application applicationEntity = Application.builder()
        .applicationId(1L)
        .build();

    when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(applicationEntity));
    when(judgmentRepository.findByApplicationId(findId)).thenReturn(Optional.ofNullable(entity));

    Response actual = judgmentService.getJudgmentOfApplication(findId);

    assertThat(actual.getJudgmentId()).isSameAs(findId);
  }

  @Test
  void Should_ReturnUpdatedResponseOfExistJudgmentEntity_When_RequestUpdateExistJudgmentInfo() {
    Long findId = 1L;

    Judgment entity = Judgment.builder()
        .judgmentId(1L)
        .name("Member Kim")
        .build();

    Request request = Request.builder()
        .name("Member Lee")
        .build();

    when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));
    when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);

    Response actual = judgmentService.update(findId, request);

    assertThat(actual.getJudgmentId()).isSameAs(findId);
    assertThat(actual.getName()).isSameAs(request.getName());
  }

  @Test
  void Should_ReturnUpdatedResponseOfExistApplicationEntity_When_RequestGrantApprovalAmountOfJudgmentInfo() {
    Long findId = 1L;

    Judgment judgmentEntity = Judgment.builder()
        .name("Member Kim")
        .applicationId(findId)
        .approvalAmount(BigDecimal.valueOf(50000000))
        .build();

    Application applicationEntity = Application.builder()
        .applicationId(findId)
        .approvalAmount(BigDecimal.valueOf(50000000))
        .build();

    when(judgmentRepository.findById(findId)).thenReturn(Optional.ofNullable(judgmentEntity));
    when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(applicationEntity));
    when(applicationRepository.save(ArgumentMatchers.any(Application.class))).thenReturn(applicationEntity);

    GrantAmount actual = judgmentService.grant(findId);

    assertThat(actual.getApplicationId()).isSameAs(findId);
    assertThat(actual.getApprovalAmount()).isSameAs(judgmentEntity.getApprovalAmount());
  }

  @Test
  void Should_DeletedJudgmentEntity_When_RequestDeleteExistJudgmentInfo() {
    Long targetId = 1L;

    Judgment entity = Judgment.builder()
        .judgmentId(1L)
        .build();

    when(judgmentRepository.save(ArgumentMatchers.any(Judgment.class))).thenReturn(entity);
    when(judgmentRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

    judgmentService.delete(targetId);

    assertThat(entity.getIsDeleted()).isSameAs(true);
  }
}
