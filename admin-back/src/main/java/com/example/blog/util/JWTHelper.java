package com.example.blog.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTHelper {
    private static final String key = "gd";
    private static final long overTime = 1000 * 60 * 60 * 24;

    private static SecretKey getKey() {
        byte[] encodekey = Base64.decodeBase64(key);
        SecretKey secretKey = new SecretKeySpec(encodekey, 0, encodekey.length, "AES");
        return secretKey;
    }

    public static String createJWT(Object subject) {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = getKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setIssuedAt(now)           // iat: jwt的签发时间
                .setSubject(JSON.toJSONString(subject))        // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

        // 设置过期时间
        if (overTime >= 0) {
            long expMillis = nowMillis + overTime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static String parseJWT(String jwt) throws SignatureException, MalformedJwtException, ExpiredJwtException {
        Claims claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
