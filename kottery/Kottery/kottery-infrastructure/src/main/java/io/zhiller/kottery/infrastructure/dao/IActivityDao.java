package io.zhiller.kottery.infrastructure.dao;


import io.zhiller.kottery.domain.activity.model.vo.AlterStateVO;
import io.zhiller.kottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Mapper
public interface IActivityDao {

   /**
    * 插入数据
    *
    * @param req 入参
    */
   void insert(Activity req);

   /**
    * 根据活动号查询活动信息
    *
    * @param activityId 活动号
    * @return 活动信息
    */
   Activity queryActivityById(Long activityId);

   /**
    * 变更活动状态
    *
    * @param alterStateVO  [activityId、beforeState、afterState]
    * @return 更新数量
    */
   int alterState(AlterStateVO alterStateVO);

}