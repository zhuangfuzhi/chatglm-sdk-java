package cn.fuzhizhuang.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fuzhizhuang
 * @description 用户角色
 */

@Getter
@AllArgsConstructor
public enum Role {

    /**
     * user ： 用户输入的内容
     */
    user("user"),

    /**
     * assistant ： 模型生成的内容
     */
    assistant("assistant"),

    ;

    /**
     * role code
     */
    private final String code;
}
