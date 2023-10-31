package cn.fuzhizhuang.chatglm.session;

/**
 * @author fuzhizhuang
 * @description 会话工厂接口
 */
public interface OpenAiSessionFactory {

    /**
     * 创建开启会话
     *
     * @return Open AI 会话
     */
    OpenAiSession openSession();
}
