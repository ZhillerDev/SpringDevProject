package com.example.kottery.interfaces;


import io.zhiller.kottery.domain.strategy.model.req.DrawReq;
import io.zhiller.kottery.domain.strategy.model.vo.AwardRateInfo;
import io.zhiller.kottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import io.zhiller.kottery.domain.strategy.service.draw.IDrawExec;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

  //    @Resource(name = "defaultRateRandomDrawAlgorithm")
  @Resource(name = "singleRateRandomDrawAlgorithm")
  private IDrawAlgorithm randomDrawAlgorithm;

  @Resource
  private IDrawExec drawExec;

  @Before
  public void init() {
    // 奖品信息
    List<AwardRateInfo> strategyList = new ArrayList<>();
    strategyList.add(new AwardRateInfo("一等奖：IMac", new BigDecimal("0.20")));
    strategyList.add(new AwardRateInfo("二等奖：iphone", new BigDecimal("0.30")));
    strategyList.add(new AwardRateInfo("三等奖：ipad", new BigDecimal("0.50")));

    // 初始数据
    randomDrawAlgorithm.initRateTuple(100001L, strategyList);
  }

  @Test
  public void test_randomDrawAlgorithm() {

    List<String> excludeAwardIds = new ArrayList<>();
    excludeAwardIds.add("二等奖：iphone");

    for (int i = 0; i < 20; i++) {
      System.out.println("中奖结果：" + randomDrawAlgorithm.randomDraw(100001L, excludeAwardIds));
    }
  }

  @Test
  public void test_drawExec() {
    drawExec.doDrawExec(new DrawReq("小傅哥", 10002L));
    drawExec.doDrawExec(new DrawReq("小佳佳", 10002L));
    drawExec.doDrawExec(new DrawReq("小蜗牛", 10002L));
    drawExec.doDrawExec(new DrawReq("八杯水", 10002L));
  }

}