package io.zhiller.kottery.domain.award.service.goods;

import io.zhiller.kottery.domain.award.model.req.GoodsReq;
import io.zhiller.kottery.domain.award.model.res.DistributionRes;

public interface IDistributionGoods {
  DistributionRes doDistribution(GoodsReq req);

  Integer getDistributionGoodsName();
}
