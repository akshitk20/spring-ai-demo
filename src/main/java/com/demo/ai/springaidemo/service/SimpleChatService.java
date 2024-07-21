package com.demo.ai.springaidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // immutable data holders. have getter methods
    public record ActorFilms(String actor, List<String> movies) {}

    public ActorFilms getActorFilms(String actor) {
        return chatClient.prompt()
                .user("Generate the filmography for a random actor")
                .call()
                .entity(ActorFilms.class);
    }
}
