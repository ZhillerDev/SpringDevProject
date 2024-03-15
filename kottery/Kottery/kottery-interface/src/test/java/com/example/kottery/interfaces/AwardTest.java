package com.example.kottery.interfaces;

import com.alibaba.fastjson.JSON;
import io.zhiller.kottery.common.Constants;
import io.zhiller.kottery.domain.award.model.req.GoodsReq;
import io.zhiller.kottery.domain.award.model.res.DistributionRes;
import io.zhiller.kottery.domain.award.service.factory.DistributionGoodsFactory;
import io.zhiller.kottery.domain.award.service.goods.IDistributionGoods;
import io.zhiller.kottery.domain.strategy.model.req.DrawReq;
import io.zhiller.kottery.domain.strategy.model.res.DrawResult;
import io.zhiller.kottery.domain.strategy.model.vo.DrawAwardInfo;
import io.zhiller.kottery.domain.strategy.service.draw.IDrawExec;
import io.zhiller.kottery.infrastructure.dao.IActivityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardTest {
  private final Logger logger = LoggerFactory.getLogger(AwardTest.class);

  @Resource
  IActivityDao activityDao;

  @Resource
  private IDrawExec drawExec;

  @Resource
  private DistributionGoodsFactory distributionGoodsFactory;

  @Test
  public void test_award() {
    DrawResult drawResult = drawExec.doDrawExec(
      new DrawReq("zhiller", 10001L)
    );
    Integer drawState = drawResult.getDrawState();
    if (Constants.DrawState.FAIL.getCode().equals(drawState)) {
      logger.info("未中奖");
      return;
    }

    DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();
    GoodsReq goodsReq = new GoodsReq(drawResult.getuId(), "2109313442431", drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent());

    // 根据 awardType 从抽奖工厂中获取对应的发奖服务
    IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
    DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);

    logger.info("测试结果：{}", JSON.toJSONString(distributionRes));
  }
}
