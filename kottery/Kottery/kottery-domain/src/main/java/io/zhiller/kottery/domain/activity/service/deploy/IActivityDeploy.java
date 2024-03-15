package io.zhiller.kottery.domain.activity.service.deploy;

import io.zhiller.kottery.domain.activity.model.req.ActivityConfigReq;

public interface IActivityDeploy {

    /**
     * 创建活动信息
     *
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 修改活动信息
     *
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigReq req);

}