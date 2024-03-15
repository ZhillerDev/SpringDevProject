package io.zhiller.kottery.domain.strategy.service.draw;

import io.zhiller.kottery.domain.strategy.model.req.DrawReq;
import io.zhiller.kottery.domain.strategy.model.res.DrawResult;

public interface IDrawExec {

  DrawResult doDrawExec(DrawReq req);

}
