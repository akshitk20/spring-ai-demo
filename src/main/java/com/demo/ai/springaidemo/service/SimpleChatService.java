package com.demo.ai.springaidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class SimpleChatService {

    private final ChatClient chatClient;

    public SimpleChatService(ChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    public String chat(String message) {
        return chatClient.prompt()
                .user(message) // this is question
                .call()
                .content(); // this is the answer
    }
}
