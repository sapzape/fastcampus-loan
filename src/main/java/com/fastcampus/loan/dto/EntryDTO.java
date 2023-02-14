package com.fastcampus.loan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EntryDTO implements Serializable {

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  public static class Request {

    private BigDecimal entryAmount;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  public static class Response {

    private Long applicationId;

    private BigDecimal entryAmount;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  @Setter
  public static class UpdateResponse {

    private Long applicationId;

    private BigDecimal beforeEntryAmount;

    private BigDecimal afterEntryAmount;
  }
}
