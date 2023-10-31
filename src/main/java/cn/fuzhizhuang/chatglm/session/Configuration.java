package cn.fuzhizhuang.chatglm.session;

import cn.fuzhizhuang.chatglm.IOpenAiApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;

/**
 * @author fuzhizhuang
 * @description ChatGLM 配置文件
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {

    /**
     * 智普Ai ChatGLM 请求地址
     */
    @Getter
    @Setter
    private String apiHost = "https://open.bigmodel.cn/api/paas/";

    /**
     * 智普Ai https://open.bigmodel.cn/usercenter/apikeys -apiSecretKey : {apiKey}.{apiSecret}
     */
    private String apiSecretKey;

    /**
     * apiKey
     */
    @Getter
    private String apiKey;

    /**
     * apiSecret
     */
    @Getter
    private String apiSecret;

    public void setApiSecretKey(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
        String[] split = apiSecretKey.split("\\.");
        if (split.length != 2) {
            throw new RuntimeException("invalid apiSecretKey");
        }
        this.apiKey = split[0];
        this.apiSecret = split[1];
    }

    /**
     * Api 服务
     */
    @Getter
    @Setter
    private IOpenAiApi openAiApi;


    /**
     * OK HTTP 客户端
     */
    @Getter
    @Setter
    private OkHttpClient okHttpClient;

    /**
     * 创建请求工厂
     *
     * @return 事件源工厂
     */
    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

    /**
     * okHttp 配置信息
     */
    @Setter
    @Getter
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;

    /**
     * 连接超时
     */
    @Getter
    @Setter
    private long connectTimeout = 450;

    /**
     * 写入超时
     */
    @Getter
    @Setter
    private long writeTimeout = 450;

    /**
     * 读取超时
     */
    @Getter
    @Setter
    private long readTimeout = 450;

    /**
     * http keywords
     */
    public static final String SSE_CONTENT_TYPE = "text/event-stream";
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";
}
