package com.fastcampus.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.Request;
import com.fastcampus.loan.dto.CounselDTO.Response;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {

  @InjectMocks
  CounselServiceImpl counselService;

  @Mock
  private CounselRepository counselRepository;

  @Spy
  private ModelMapper modelMapper;

  @Test
  void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
    Counsel entity = Counsel.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .memo("I hope to get a loan")
        .zipCode("123456")
        .address("Somewhere in Gangnam-gu, Seoul")
        .addressDetail("What Apartment No. 101, 1st floor No. 101")
        .build();

    Request request = Request.builder()
        .name("Member Kim")
        .cellPhone("010-1111-2222")
        .email("mail@abc.de")
        .memo("I hope to get a loan")
        .zipCode("123456")
        .address("Somewhere in Gangnam-gu, Seoul")
        .addressDetail("What Apartment No. 101, 1st floor No. 101")
        .build();

    when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

    Response actual = counselService.create(request);

    assertThat(actual.getName()).isSameAs(entity.getName());
  }
}
