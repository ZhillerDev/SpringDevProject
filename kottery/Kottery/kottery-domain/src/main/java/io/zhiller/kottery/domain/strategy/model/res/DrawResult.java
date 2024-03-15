package io.zhiller.kottery.domain.strategy.model.res;

import io.zhiller.kottery.common.Constants;
import io.zhiller.kottery.domain.strategy.model.vo.DrawAwardInfo;

public class DrawResult {

  /**
   * 用户ID
   */
  private String uId;

  /**
   * 策略ID
   */
  private Long strategyId;

  /**
   * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
   */
  private Integer drawState = Constants.DrawState.FAIL.getCode();

  /**
   * 中奖奖品信息
   */
  private DrawAwardInfo drawAwardInfo;

  public DrawResult() {
  }

  public DrawResult(String uId, Long strategyId, Integer drawState) {
    this.uId = uId;
    this.strategyId = strategyId;
    this.drawState = drawState;
  }

  public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardInfo drawAwardInfo) {
    this.uId = uId;
    this.strategyId = strategyId;
    this.drawState = drawState;
    this.drawAwardInfo = drawAwardInfo;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public Long getStrategyId() {
    return strategyId;
  }

  public void setStrategyId(Long strategyId) {
    this.strategyId = strategyId;
  }

  public Integer getDrawState() {
    return drawState;
  }

  public void setDrawState(Integer drawState) {
    this.drawState = drawState;
  }

  public DrawAwardInfo getDrawAwardInfo() {
    return drawAwardInfo;
  }

  public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
    this.drawAwardInfo = drawAwardInfo;
  }

}
