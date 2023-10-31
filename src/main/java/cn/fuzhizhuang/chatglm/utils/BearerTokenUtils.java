package cn.fuzhizhuang.chatglm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author fuzhizhuang
 * @description 签名工具包
 */
@Slf4j
public class BearerTokenUtils {

    /**
     * 过期时间：默认30分钟，单位：毫秒
     */
    private static final long expireMillisecond = 30 * 60 * 1000L;

    /**
     * 缓存服务
     */
    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            //缓存时间29分钟
            .expireAfterWrite(expireMillisecond - (60 * 1000L), TimeUnit.MILLISECONDS)
            .build();


    /**
     * 对ApiKey进行签名
     *
     * @param apiKey    登录创建ApiKey <a href="https://open.bigmodel.cn/usercenter/apikeys">apiKeys</a>
     * @param apiSecret apiKey的后半部分,{id}.{secret}中的secret
     * @return token 使用token进行用户鉴权
     */
    public static String generateToken(String apiKey, String apiSecret) {
        // 缓存Token
        String token = cache.getIfPresent(apiKey);
        if (null != token) return token;
        //创建token
        Algorithm algorithm = Algorithm.HMAC256(apiSecret.getBytes(StandardCharsets.UTF_8));
        //{"alg":"HS256","sign_type":"SIGN"}
        //alg : 属性表示签名使用的算法，默认为 HMAC SHA256（写为HS256）
        //sign_type : 属性表示令牌的类型，JWT 令牌统一写为 SIGN
        HashMap<String, Object> header = new HashMap<>();
        header.put("alg","HS256");
        header.put("sign_type","SIGN");
        HashMap<String, Object> payload = new HashMap<>();
        //api_key : 属性表示用户标识 id，即用户API Key的{id}部分
        payload.put("api_key",apiKey);
        //exp : 属性表示生成的JWT的过期时间，客户端控制，单位为毫秒
        payload.put("exp",System.currentTimeMillis() + expireMillisecond);
        //timestamp : 属性表示当前时间戳，单位为毫秒
        payload.put("timestamp",Calendar.getInstance().getTimeInMillis());
        //生成token信息
        token = JWT.create().withHeader(header).withPayload(payload).sign(algorithm);
        //将token存入缓存
        cache.put(apiKey, token);
        return token;
    }
}
