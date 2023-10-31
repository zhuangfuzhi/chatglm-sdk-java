package cn.fuzhizhuang.chatglm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fuzhizhuang
 * @description 请求参数实体类
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatCompletionRequest {

    /**
     * AI模型,默认使用ChatGLM_6B_SSE
     */
    private Model model = Model.CHATGLM_6B_SSE;

    /**
     * 调用对话模型时，将当前对话信息列表作为提示输入给模型;
     * 按照 {"role": "user", "content": "你好"} 的键值对形式进行传参;
     * 总长度超过模型最长输入限制后会自动截断，需按时间由旧到新排序
     * 用户输入的内容：role:user
     * 挟带历史的内容：role:assistant
     */
    private List<Prompt> prompt;

    /**
     * 采样温度，控制输出的随机性，必须为正数
     * 取值范围是：(0.0,1.0]，不能等于 0,默认值为 0.95
     * 值越大，会使输出更随机，更具创造性；值越小，输出会更加稳定或确定
     * 建议您根据应用场景调整 top_p 或 temperature 参数，但不要同时调整两个参数
     */
    private float temperature = 0.95f;

    /**
     * 用温度取样的另一种方法，称为核取样
     * 取值范围是：(0.0, 1.0) 开区间，不能等于 0 或 1，默认值为 0.7
     * 模型考虑具有 top_p 概率质量tokens的结果
     * 例如：0.1 意味着模型解码器只考虑从前 10% 的概率的候选集中取tokens
     * 建议您根据应用场景调整 top_p 或 temperature 参数，但不要同时调整两个参数
     */
    @JsonProperty("top_p")
    private float topP = 0.7f;

    /**
     * 由用户端传参，需保证唯一性；用于区分每次请求的唯一标识，用户端不传时平台会默认生成
     */
    @JsonProperty("request_id")
    private String requestId = String.format("fuzhi-%d",System.currentTimeMillis());

    /**
     * SSE接口调用时，用于控制每次返回内容方式是增量还是全量，不提供此参数时默认为增量返回
     * - true 为增量返回
     * - false 为全量返回
     */
    private boolean incremental = true;

    /**
     * sseFormat 用于兼容解决sse增量模式okhttpsse截取data:后面空格问题，[data: hello].只在增量模式下使用sseFormat
     */
    private String sseFormat = "data";

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Prompt {

        /**
         * 本条信息作者的角色，可选择user或assistant
         * - user : 用户角色输入信息
         * - assistant : 模型返回的信息
         */
        private String role;

        /**
         * 本条信息的具体内容
         */
        private String content;
    }

    @Override
    public String toString() {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("request_id",requestId);
        paramsMap.put("prompt",prompt);
        paramsMap.put("temperature",temperature);
        paramsMap.put("top_p",topP);
        paramsMap.put("incremental",incremental);
        paramsMap.put("sseFormat",sseFormat);
        try {
            return new ObjectMapper().writeValueAsString(paramsMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
