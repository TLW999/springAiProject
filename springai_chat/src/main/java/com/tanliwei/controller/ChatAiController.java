package com.tanliwei.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatAiController {

    @Autowired
    private ChatClient chatClient;

    //角色预设，非流式响应
    @GetMapping("/chatai")
    public String chat(@RequestParam(value = "msg") String message) {
        return chatClient.prompt().user(message).call().content(); //非流式输出
    }

    //角色预设，流式响应
    @GetMapping(value = "/chataistream" , produces = "text/html;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam(value = "msg") String message) {
        return chatClient.prompt().user(message).stream().content(); //非流式输出
    }
}
