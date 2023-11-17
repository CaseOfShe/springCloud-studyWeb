package com.mrone.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/14 14:07
 * @Description:   生产token 校验token 解码token
 */


/**
 * token管理
 *
 * @author Ding
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.expire}")
    private Integer expire;

    @Value("${jwt.secret}")
    private String secret;

    /**
     *
     * @param adminId
     * @param adminPwd
     * @return 管理员id
//     */

    /**
     * 生成token
     *
     * @param openid   用户id
     * @param openid 用于区别客户端，如移动端，网页端，此处可根据自己业务自定义
     */
    public String createToken(Object openid) {
        Date validity = new Date((new Date()).getTime() + expire * 1000);
        return Jwts.builder()
                // 代表这个JWT的主体，即它的所有人
                .setSubject(String.valueOf(openid))
                // 代表这个JWT的签发主体
                .setIssuer("")
                // 是一个时间戳，代表这个JWT的签发时间；
                .setIssuedAt(new Date())
                // 代表这个JWT的接收对象
//                .setAudience(clientId)
                // 放入用户id
                .claim("openid", openid)
                // 自定义信息
                .claim("MrOne", "MrOne")
                .signWith(SignatureAlgorithm.HS512, this.getSecretKey())
                .setExpiration(validity)
                .compact();
    }

    public String createToken2(String phone) {
        Date validity = new Date((new Date()).getTime() + expire * 1000);
        return Jwts.builder()
                // 代表这个JWT的主体，即它的所有人
                .setSubject(String.valueOf(phone))
                // 代表这个JWT的签发主体
                .setIssuer("")
                // 是一个时间戳，代表这个JWT的签发时间；
                .setIssuedAt(new Date())
                // 代表这个JWT的接收对象
//                .setAudience(clientId)
                // 放入用户id
                .claim("phone", phone)
                // 自定义信息
                .claim("MrOne", "MrOne")
                .signWith(SignatureAlgorithm.HS512, this.getSecretKey())
                .setExpiration(validity)
                .compact();
    }




    /**
     * 校验token
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("无效的token：" + authToken);
        }
        return false;
    }

    /**
     * 解码token openid
     */
    public String decodeToken(String token) {
        if (validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(token).getBody();
            // 客户端id
            String clientId = claims.getAudience();
            // 用户id
            Object openid = claims.get("openid");
            log.info("token有效,openid:{}", openid);
            return String.valueOf(openid);
        }
        log.error("***token无效***");
        return null;
    }

    /**
     *
     * @param token
     * @return
     */
    public String decodeToken2(String token) {
        if (validateToken(token)) {
            Claims claims = Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(token).getBody();
            // 客户端id
            String clientId = claims.getAudience();
            // 用户id
            Object phone = claims.get("phone");
            log.info("token有效,phone:{}", phone);
            return String.valueOf(phone);
        }
        log.error("***token无效***");
        return null;
    }




    private String getSecretKey() {
        return Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }
}
