package cn.fuzhizhuang.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fuzhizhuang
 * @description 会话模型枚举
 */
@Getter
@AllArgsConstructor
public enum Model {

    CHATGLM_6B_SSE("chatglm_6b_sse","ChatGLM-6B 测试模型"),
    CHATGLM_LITE("chatglm_lite","轻量版模型,适用于对推理速度和成本敏感的场景"),
    CHATGLM_LITE_32K("chatglm_lite_32k","标准版模型,适用于兼顾效果和成本的场景"),
    CHATGLM_STD("chatglm_std","适用于对知识量、推理能力、创造力要求较高的场景"),
    CHATGLM_PRO("chatglm_pro","适用于对知识量、推理能力、创造力要求更高的场景"),
    CHATGLM_TURBO("chatglm_turbo","根据输入的自然语言指令完成多种语言类任务"),
    ;


    /**
     * AI模型
     */
    private final String model;

    /**
     * 模型描述
     */
    private final String description;

}
