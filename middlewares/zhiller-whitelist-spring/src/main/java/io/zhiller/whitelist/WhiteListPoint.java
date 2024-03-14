package io.zhiller.whitelist;

import com.alibaba.fastjson.JSON;
import io.zhiller.whitelist.annotation.JoinWhiteList;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class WhiteListPoint {
  private Logger logger = LoggerFactory.getLogger(WhiteListPoint.class);

  @Resource
  private String whiteListConfig;

  @Pointcut("@annotation(io.zhiller.whitelist.annotation.JoinWhiteList)")
  public void point() {
  }

  @Around("point()")
  public Object doRouter(ProceedingJoinPoint joinPoint) throws Throwable {
    Method method = getMethod(joinPoint);
    JoinWhiteList whiteList = method.getAnnotation(JoinWhiteList.class);

    String fieldValue = getFieldValue(whiteList.key(), joinPoint.getArgs());
    logger.info("middleware method:{} value:{}", method.getName(), method.getDefaultValue());
    if (null == fieldValue || fieldValue.isEmpty()) return joinPoint.proceed();

    String[] split = whiteListConfig.split(",");
    for (String str : split) {
      if (fieldValue.equals(str)) return joinPoint.proceed();
    }

    return returnObject(whiteList, method);
  }

  private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
  }

  private Object returnObject(JoinWhiteList whiteList, Method method) throws InstantiationException, IllegalAccessException {
    Class<?> returnType = method.getReturnType();
    String returnJson = whiteList.returnJson();
    if ("".equals(returnJson)) {
      return returnType.newInstance();
    }
    return JSON.parseObject(returnJson, returnType);
  }

  private String getFieldValue(String field, Object[] args) {
    String fieldValue = null;
    for (Object arg : args) {
      try {
        if (null == fieldValue || fieldValue.isEmpty()) {
          fieldValue = BeanUtils.getProperty(arg, field);
        } else {
          break;
        }
      } catch (Exception e) {
        if (args.length == 1) {
          return args[0].toString();
        }
      }
    }
    return fieldValue;
  }
}
