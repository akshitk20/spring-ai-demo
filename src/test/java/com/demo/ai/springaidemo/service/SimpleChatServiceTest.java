package com.demo.ai.springaidemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleChatServiceTest {

    @Autowired
    private SimpleChatService service;

    @Test
    void testChatClient() {
        String response = service.chat("Tell me about spring AI project");
        assertNotNull(response);
        assertTrue(!response.isEmpty());
    }

    @Test
    void testGetActorFilms() {
        SimpleChatService.ActorFilms actorFilms = service.getActorFilms("Tom Hanks");
        System.out.println(actorFilms);
    }

    @Test
    void testGetActorFilmsList() {
        SimpleChatService.ActorFilmsList actorFilms = service.getActorFilmsList("Tom Hanks","Margot Robbie");
        System.out.println(actorFilms);
    }

    @Test
    void testGetActorFilmsListAsync() {
        service.getActorFilmsListAsync("Tom Hanks","Margot Robbie")
                .doOnNext(System.out::println)
                .blockLast();
    }
}