package io.zhiller.kottery.domain.strategy.repository;

import io.zhiller.kottery.domain.strategy.model.aggregates.StrategyRich;
import io.zhiller.kottery.domain.strategy.model.vo.AwardBriefVO;


import java.util.List;

public interface IStrategyRepository {

  StrategyRich queryStrategyRich(Long strategyId);

  AwardBriefVO queryAwardInfo(String awardId);

  List<String> queryNoStockStrategyAwardList(Long strategyId);

  /**
   * 扣减库存
   * @param strategyId 策略ID
   * @param awardId    奖品ID
   * @return           扣减结果
   */
  boolean deductStock(Long strategyId, String awardId);

}
