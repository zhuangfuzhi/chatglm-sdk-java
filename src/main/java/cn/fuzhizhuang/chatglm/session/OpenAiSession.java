package cn.fuzhizhuang.chatglm.session;

import cn.fuzhizhuang.chatglm.model.ChatCompletionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @author fuzhizhuang
 * @description 会话服务接口
 */
public interface OpenAiSession {

    /**
     * 会话请求
     *
     * @param chatCompletionRequest 聊天完成请求
     * @param eventSourceListener   事件源侦听器
     * @return 事件源
     * @throws JsonProcessingException JSON 处理异常
     */
    EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;
}
