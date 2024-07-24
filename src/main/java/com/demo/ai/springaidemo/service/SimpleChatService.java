package com.demo.ai.springaidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class SimpleChatService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;

    public SimpleChatService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel)
                .defaultAdvisors(new PromptChatMemoryAdvisor(new InMemoryChatMemory())) // advisor -> intercepts the request and modifies it. this is used to preserve the state of request
                .build();
    }

    public String chat(String message) {
        return chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(systemResource) // update in the prompt
                        .param("name","Bob")
                        .param("voice", "pirate"))
                .user(message) // this is question
                .call()
                .content(); // this is the answer
    }

    // immutable data holders. have getter methods
    public record ActorFilms(String actor, List<String> movies) {}
    public record ActorFilmsList(List<ActorFilms> actorFilms) {}
    public ActorFilms getActorFilms(String actor) {
        return chatClient.prompt()
                .user("Generate the filmography for a random actor")
                .call()
                .entity(ActorFilms.class);
    }

    public ActorFilmsList getActorFilmsList(String... actors) {
        String allActors = String.join(",",actors);
        return chatClient.prompt()
                .user("Generate the filmography for " + allActors)
                .call()
                .entity(ActorFilmsList.class);
    }

    public Flux<String> getActorFilmsListAsync(String... actors) {
        String allActors = String.join(",",actors);
        return chatClient.prompt()
                .user("Generate the filmography for " + allActors)
                .stream()
                .content();
    }

    public String vision(String message, Resource image) {
        return chatClient.prompt()
                .user(u -> u.text(message)
                        .media(MimeTypeUtils.IMAGE_PNG, image))
                .call()
                .content();
    }
}
