package com.fastcampus.loan.domain;

import java.math.BigDecimal;
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
public class Application extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long applicationId;

  @Column(columnDefinition = "varchar(12) DEFAULT NULL COMMENT '신청자'")
  private String name;

  @Column(columnDefinition = "varchar(13) DEFAULT NULL COMMENT '전화번호'")
  private String cellPhone;

  @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '신청자 이메일'")
  private String email;

  @Column(columnDefinition = "decimal(5,4) DEFAULT NULL COMMENT '금리'")
  private BigDecimal interestRate;

  @Column(columnDefinition = "decimal(5,4) DEFAULT NULL COMMENT '취급수수료'")
  private BigDecimal fee;

  @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '만기'")
  private LocalDateTime maturity;

  @Column(columnDefinition = "decimal(15,2) DEFAULT NULL COMMENT '대출 신청 금액'")
  private BigDecimal hopeAmount;

  @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '신청일자'")
  private LocalDateTime appliedAt;

  @Column(columnDefinition = "decimal(15,2) DEFAULT NULL COMMENT '승인 금액'")
  private BigDecimal approvalAmount;

  @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '약정일자'")
  private LocalDateTime contractedAt;
}
