package com.fastcampus.loan.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_deleted=false")
public class Counsel extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long counselId;

  @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '신청일자'")
  private LocalDateTime appliedAt;

  @Column(columnDefinition = "varchar(12) DEFAULT NULL COMMENT '상담 요청자'")
  private String name;

  @Column(columnDefinition = "varchar(13) DEFAULT NULL COMMENT '전화번호'")
  private String cellPhone;

  @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '책임자 이메일'")
  private String email;

  @Column(columnDefinition = "text DEFAULT NULL COMMENT '상담 메모'")
  private String memo;

  @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소'")
  private String address;

  @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소 상세'")
  private String addressDetail;

  @Column(columnDefinition = "varchar(5) DEFAULT NULL COMMENT '우편번호'")
  private String zipCode;
}
