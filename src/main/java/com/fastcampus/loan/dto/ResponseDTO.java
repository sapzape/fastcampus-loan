package com.fastcampus.loan.dto;

import com.fastcampus.loan.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class ResponseDTO<T> implements Serializable {

  private ResultObject result;

  private T data;

  public ResponseDTO(ResultObject result) {
    this.result = result;
  }

  public ResponseDTO(T data) {
    this.data = data;
  }

  public static <T> ResponseDTO<T> ok() {
    return new ResponseDTO<>(ResultObject.getSuccess());
  }

  public static <T> ResponseDTO<T> ok(T data) {
    return new ResponseDTO<>(ResultObject.getSuccess(), data);
  }

  public static <T> ResponseDTO<T> response(T data) {
    return new ResponseDTO<>(ResultObject.getSuccess(), data);
  }

  public ResponseDTO(BaseException ex) {
    this.result = new ResultObject(ex);
  }
}
