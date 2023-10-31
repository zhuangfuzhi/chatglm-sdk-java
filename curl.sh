curl -X POST \
        -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInNpZ25fdHlwZSI6IlNJR04iLCJ0eXAiOiJKV1QifQ.eyJhcGlfa2V5IjoiM2JkZTQxNWNkY2ZjYTgwZmJlNzA2OWY5NTA0MzQxYjEiLCJleHAiOjE2OTg3NTA5MDUyNDUsInRpbWVzdGFtcCI6MTY5ODc0OTEwNTI0NX0.hVivnfTaxpFxZQ0Nyxffk9AqBcjuNCOcN2B4fYPn8kI" \
        -H "Content-Type: application/json" \
        -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
        -H "Accept: text/event-stream" \
        -d '{
        "top_p": 0.7,
        "sseFormat": "data",
        "temperature": 0.95,
        "incremental": true,
        "request_id": "fuzhi-1696992276607",
        "prompt": [
        {
        "role": "user",
        "content": "讲一个笑话"
        }
        ]
        }' \
  http://open.bigmodel.cn/api/paas/v3/model-api/chatglm_lite/sse-invoke