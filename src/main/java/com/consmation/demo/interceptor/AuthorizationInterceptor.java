package com.consmation.demo.interceptor;

import com.consmation.demo.autoconfig.properties.InterfaceProperties;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:19:14
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private InterfaceProperties interfaceProperties;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //白名单校验
        if (ObjectUtils.isEmpty(interfaceProperties) || ObjectUtils.isEmpty(interfaceProperties.getWhiteList())) {
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        }
        //获取url
        StringBuffer requestURL = request.getRequestURL();  //  http://localhost:8888/demo/limit
        //校验url
        if (StringUtils.isEmpty(requestURL)) {
            throw new CustomException(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        URL url = new URL(requestURL.toString());
        String host = url.getHost();
        //ip校验
        List<String> whiteList = interfaceProperties.getWhiteList();
        if (!whiteList.contains(host)) {
            throw new CustomException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        return true;
    }
}
