package com.nike.douye.aspect;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nike.douye.Enum.Code;
import com.nike.douye.annotation.CheckToken;
import com.nike.douye.annotation.LoginToken;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.UserService;
import com.nike.douye.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * token拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginToken注释，有则跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new BaseException("无token，请重新登录",Code.PARAM_MISSING.getValue());
                }

                // 获取 token 中的 用户id
                String id;
                try {
                    id = JWT.decode(token).getClaim("id").asString();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("访问异常！");
                }

                UserDTO user = userService.queryUserById(Integer.valueOf(id));
                if (user == null) {
                    throw new BaseException("用户不存在，请重新登录",Code.PARAM_ERROR.getValue());
                }
                Boolean verify;
                    try{
                        verify = JwtUtil.isVerify(token, user);
                    }catch (Exception e){
                        throw new BaseException("token已过期",Code.PARAM_ERROR.getValue());
                    }
                    if (!verify) {
                        throw new BaseException("token无效哦！",Code.PARAM_ERROR.getValue());
                    }

                return true;
            }
            }
        return true;
        }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}