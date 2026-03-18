package com.tanliwei.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RagController {
    @Autowired
    private ChatClient dashScopeChatClient;

    @Autowired
    private VectorStore vectorStore;

    @GetMapping(value = "/chat", produces = "text/plain; charset=UTF-8")
    public String generate(String userInput){
        String content = dashScopeChatClient.prompt()
                .user(userInput)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
        return content;
    }
}
