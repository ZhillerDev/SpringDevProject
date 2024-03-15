package io.zhiller.kottery.domain.award.service.goods;

import io.zhiller.kottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class DistributionBase {
  protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

  @Resource
  private IAwardRepository awardRepository;

  protected void updateUserAwardState(String uId, String orderId, String awardId, Integer awardState, String awardStateInfo) {
    // TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态
    logger.info("TODO 后期添加更新分库分表中，用户个人的抽奖记录表中奖品发奖状态 uId：{}", uId);
  }
}
