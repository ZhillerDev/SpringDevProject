package io.zhiller.kottery.domain.strategy.model.vo;

import java.math.BigDecimal;

public class AwardRateInfo {

  // 奖品ID
  private String awardId;

  // 中奖概率
  private BigDecimal awardRate;

  public AwardRateInfo() {
  }

  public AwardRateInfo(String awardId, BigDecimal awardRate) {
    this.awardId = awardId;
    this.awardRate = awardRate;
  }

  public String getAwardId() {
    return awardId;
  }

  public void setAwardId(String awardId) {
    this.awardId = awardId;
  }

  public BigDecimal getAwardRate() {
    return awardRate;
  }

  public void setAwardRate(BigDecimal awardRate) {
    this.awardRate = awardRate;
  }
}
