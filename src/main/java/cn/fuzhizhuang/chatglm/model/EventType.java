package cn.fuzhizhuang.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fuzhizhuang
 * @description 消息类型
 */
@Getter
@AllArgsConstructor
public enum EventType {

    add("add","增量"),
    finish("finish","结束"),
    error("error","错误"),
    interrupted("interrupted","中断"),

    ;

    /**
     * event code
     */
    private final String code;

    /**
     * event description
     */
    private final String description;
}
