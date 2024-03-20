package io.zhiller.gpt.domain.security.service;

import io.zhiller.gpt.domain.security.model.vo.JwtToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AccessControlFilter {
  private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

  @Override
  protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
    return false;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    // 如果你设定的 token 放到 header 中，则可以这样获取；request.getHeader("Authorization");
    JwtToken jwtToken = new JwtToken(request.getParameter("token"));
    try {
      // 鉴权认证
      getSubject(servletRequest, servletResponse).login(jwtToken);
      logger.info("鉴权成功");
      return true;
    } catch (Exception e) {
      logger.error("鉴权认证失败", e);
      onLoginFail(servletResponse);
      return false;
    }
  }

  private void onLoginFail(ServletResponse response) throws IOException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    httpResponse.getWriter().write("Auth Err!");
  }
}
