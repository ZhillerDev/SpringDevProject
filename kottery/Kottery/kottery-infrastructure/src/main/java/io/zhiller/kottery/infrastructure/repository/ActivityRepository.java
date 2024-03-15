package io.zhiller.kottery.infrastructure.repository;

import io.zhiller.kottery.common.Constants;
import io.zhiller.kottery.domain.activity.model.vo.*;
import io.zhiller.kottery.domain.activity.repository.IActivityRepository;
import io.zhiller.kottery.infrastructure.dao.IActivityDao;
import io.zhiller.kottery.infrastructure.dao.IAwardDao;
import io.zhiller.kottery.infrastructure.dao.IStrategyDao;
import io.zhiller.kottery.infrastructure.dao.IStrategyDetailDao;
import io.zhiller.kottery.infrastructure.po.Activity;
import io.zhiller.kottery.infrastructure.po.Award;
import io.zhiller.kottery.infrastructure.po.Strategy;
import io.zhiller.kottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityRepository implements IActivityRepository {

  @Resource
  private IActivityDao activityDao;
  @Resource
  private IAwardDao awardDao;
  @Resource
  private IStrategyDao strategyDao;
  @Resource
  private IStrategyDetailDao strategyDetailDao;

  @Override
  public void addActivity(ActivityVO activity) {
    Activity req = new Activity();
    BeanUtils.copyProperties(activity, req);
    activityDao.insert(req);
  }

  @Override
  public void addAward(List<AwardVO> awardList) {
    List<Award> req = new ArrayList<>();
    for (AwardVO awardVO : awardList) {
      Award award = new Award();
      BeanUtils.copyProperties(awardVO, award);
      req.add(award);
    }
    awardDao.insertList(req);
  }

  @Override
  public void addStrategy(StrategyVO strategy) {
    Strategy req = new Strategy();
    BeanUtils.copyProperties(strategy, req);
    strategyDao.insert(req);
  }

  @Override
  public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailList) {
    List<StrategyDetail> req = new ArrayList<>();
    for (StrategyDetailVO strategyDetailVO : strategyDetailList) {
      StrategyDetail strategyDetail = new StrategyDetail();
      BeanUtils.copyProperties(strategyDetailVO, strategyDetail);
      req.add(strategyDetail);
    }
    strategyDetailDao.insertList(req);
  }

  @Override
  public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
    AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState) beforeState).getCode(), ((Constants.ActivityState) afterState).getCode());
    int count = activityDao.alterState(alterStateVO);
    return 1 == count;
  }

}
