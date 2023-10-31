package cn.fuzhizhuang.chatglm;


import cn.fuzhizhuang.chatglm.model.ChatCompletionRequest;
import cn.fuzhizhuang.chatglm.model.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author fuzhizhuang
 * @description OpenAi接口，用于扩展通用类服务
 */
public interface IOpenAiApi {

    String v3_completions = "api/paas/v3/model-api/{model}/sse-invoke";

    @POST(v3_completions)
    Single<ChatCompletionResponse> completions(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);

}