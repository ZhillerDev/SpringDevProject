package io.zhiller.kottery.domain.strategy.service.draw;

import io.zhiller.kottery.common.Constants;
import io.zhiller.kottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DrawConfig {

  @Resource(name = "defaultRateRandomDrawAlgorithm")
  private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

  @Resource(name = "singleRateRandomDrawAlgorithm")
  private IDrawAlgorithm singleRateRandomDrawAlgorithm;

  /**
   * 抽奖策略组
   */
  protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

  @PostConstruct
  public void init() {
    drawAlgorithmGroup.put(Constants.StrategyMode.ENTIRETY.getCode(), defaultRateRandomDrawAlgorithm);
    drawAlgorithmGroup.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
  }
}
