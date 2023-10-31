package cn.fuzhizhuang.chatglm.model;

import lombok.Data;

/**
 * @author fuzhizhuang
 * @description 请求返回结果实体类
 */
@Data
public class ChatCompletionResponse {

    /**
     * 具体内容
     */
    private String data;

    /**
     * finish 事件时，通过 meta 发送更多信息，比如数量统计信息 usage
     */
    private String meta;

    @Data
    public static class Meta {
        /**
         * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）
         * 注：处理中状态需通过查询获取结果
         */
        private String task_status;

        /**
         * 本次模型调用的 tokens 数量统计
         */
        private Usage usage;

        /**
         * 智谱AI开放平台生成的任务订单号，调用请求结果接口时请使用此订单号
         */
        private String task_id;

        /**
         * 用户在客户端请求时提交的任务编号或者平台生成的任务编号
         */
        private String request_id;
    }

    @Data
    private static class Usage {
        /**
         * 模型输出的 tokens 数量
         */
        private int completion_tokens;

        /**
         * 用户输入的 tokens 数量
         */
        private int prompt_tokens;

        /**
         * 总 tokens 数量
         */
        private int total_tokens;
    }
}
