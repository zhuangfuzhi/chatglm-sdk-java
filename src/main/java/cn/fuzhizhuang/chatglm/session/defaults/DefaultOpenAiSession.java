package cn.fuzhizhuang.chatglm.session.defaults;

import cn.fuzhizhuang.chatglm.IOpenAiApi;
import cn.fuzhizhuang.chatglm.model.ChatCompletionRequest;
import cn.fuzhizhuang.chatglm.session.Configuration;
import cn.fuzhizhuang.chatglm.session.OpenAiSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author fuzhizhuang
 * @description 会话服务
 */
public class DefaultOpenAiSession implements OpenAiSession {


    /**
     * 配置
     */
    private final Configuration configuration;

    /**
     * 工厂事件
     */
    private final EventSource.Factory factory;

    /**
     * openAi接口
     */
    private IOpenAiApi openAiApi;

    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.factory = configuration.createRequestFactory();
        this.openAiApi = configuration.getOpenAiApi();
    }

    @Override
    public EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        //构建请求信息
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(IOpenAiApi.v3_completions).replace("{model}", chatCompletionRequest.getModel().getModel()))
                .post(RequestBody.create(MediaType.parse("application/json"), chatCompletionRequest.toString()))
                .build();
        //返回事件结果
        return factory.newEventSource(request, eventSourceListener);
    }
}
