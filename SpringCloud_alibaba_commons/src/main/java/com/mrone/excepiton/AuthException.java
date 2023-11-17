package com.mrone.excepiton;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/15 10:59
 * @Description: 权限不足异常捕获
 */
@ControllerAdvice
public class AuthException {

    /**
     *
     * @param request
     * @param response
     * @param exception
     * @return 未登录
     */

    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result<Object> handleShiroException(HttpServletRequest request, HttpServletResponse response, UnauthorizedException exception) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.PERMISSION_UNAUTHORISE.code())
                .setMessage(ResultCode.PERMISSION_UNAUTHORISE.message());
        return result;
    }


    /**
     *
     * @param request
     * @param response
     * @param exception
     * @return 未授权
     */

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Result<Object> AuthorizationException(HttpServletRequest request, HttpServletResponse response, AuthorizationException exception) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.PERMISSION_UNAUTHORISE.code())
                .setMessage(ResultCode.PERMISSION_UNAUTHORISE.message());
        return result;
    }



    /**
     * 请求地址访问错误
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Object> ArgumentException(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException exception){
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.DATA_IS_WRONG.code())
                .setMessage(ResultCode.DATA_IS_WRONG.message());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public Result<Object> AuthenticationException(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.PERMISSION_UNAUTHORISE.code())
                .setMessage(ResultCode.PERMISSION_UNAUTHORISE.message());
        return result;
    }


    @ResponseBody
    @ExceptionHandler(value = RedisSystemException.class)
    public Result<Object> RedisSystemException(HttpServletRequest request, HttpServletResponse response, RedisSystemException exception){
        Result<Object> result = new Result<>();
        result.setCode(ResultCode.INTERFACE_EXCEED_LOAD.code())
                .setMessage(ResultCode.INTERFACE_EXCEED_LOAD.message());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = ClassCastException.class)
    public Result<Object> ClassCastException(HttpServletRequest request, HttpServletResponse response, ClassCastException exception){
        Result<Object> result = new Result<>();
        result.setCode(500)
                .setMessage("请勿重复刷赞");
        return result;
    }


}




