package com.singhow.common;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ApiRequester {

    public JsonNode request(String url){
        WebClient webClient = WebClient.create();
        Mono<JsonNode> mono = webClient
                .get() // GET 请求
                .uri(url)  // 请求路径
                .retrieve() // 获取响应体
                .bodyToMono(JsonNode.class); // 数据转为JsonNode格式

        return mono.block();
    }
}
