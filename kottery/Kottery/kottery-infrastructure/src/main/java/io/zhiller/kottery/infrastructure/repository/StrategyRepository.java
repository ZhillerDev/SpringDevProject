package io.zhiller.kottery.infrastructure.repository;

import io.zhiller.kottery.domain.strategy.model.aggregates.StrategyRich;
import io.zhiller.kottery.domain.strategy.model.vo.AwardBriefVO;
import io.zhiller.kottery.domain.strategy.model.vo.StrategyBriefVO;
import io.zhiller.kottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import io.zhiller.kottery.domain.strategy.repository.IStrategyRepository;
import io.zhiller.kottery.infrastructure.dao.IAwardDao;
import io.zhiller.kottery.infrastructure.dao.IStrategyDao;
import io.zhiller.kottery.infrastructure.dao.IStrategyDetailDao;
import io.zhiller.kottery.infrastructure.po.Award;
import io.zhiller.kottery.infrastructure.po.Strategy;
import io.zhiller.kottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class StrategyRepository implements IStrategyRepository {

  @Resource
  private IStrategyDao strategyDao;

  @Resource
  private IStrategyDetailDao strategyDetailDao;

  @Resource
  private IAwardDao awardDao;

  @Override
  public StrategyRich queryStrategyRich(Long strategyId) {
    Strategy strategy = strategyDao.queryStrategy(strategyId);
    List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

    StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
    BeanUtils.copyProperties(strategy, strategyBriefVO);

    List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
    for (StrategyDetail strategyDetail : strategyDetailList) {
      StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
      BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
      strategyDetailBriefVOList.add(strategyDetailBriefVO);
    }

    return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
  }

  @Override
  public AwardBriefVO queryAwardInfo(String awardId) {

    Award award = awardDao.queryAwardInfo(awardId);

    // 可以使用 BeanUtils.copyProperties(award, awardBriefVO)、或者基于ASM实现的Bean-Mapping，但在效率上最好的依旧是硬编码
    AwardBriefVO awardBriefVO = new AwardBriefVO();
    awardBriefVO.setAwardId(award.getAwardId());
    awardBriefVO.setAwardType(award.getAwardType());
    awardBriefVO.setAwardName(award.getAwardName());
    awardBriefVO.setAwardContent(award.getAwardContent());

    return awardBriefVO;
  }

  @Override
  public List<String> queryNoStockStrategyAwardList(Long strategyId) {
    return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
  }

  @Override
  public boolean deductStock(Long strategyId, String awardId) {
    StrategyDetail req = new StrategyDetail();
    req.setStrategyId(strategyId);
    req.setAwardId(awardId);
    int count = strategyDetailDao.deductStock(req);
    return count == 1;
  }

}
