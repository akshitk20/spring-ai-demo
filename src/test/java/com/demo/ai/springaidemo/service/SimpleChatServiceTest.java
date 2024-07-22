package com.demo.ai.springaidemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleChatServiceTest {

    @Autowired
    private SimpleChatService service;

    @Test
    void testChatClient() {
        String prompt = """
                    Write a cover letter for Java Developer position in AI and machine learning
                """;
        String response = service.chat(prompt);
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

    @Test
    void testChat() {
        String response = service.chat("Hi I am slim shady");
        System.out.println(response);
        response = service.chat("Whats my name?");
        System.out.println(response);
    }

    @Value("classpath:/start_spring_io_screen.png")
    private Resource resource;
    @Test
    void testVision() {
        String response = service.vision("""
                    Here is screenshot of spring IO starter page. what do i need to add or change
                """, resource);
        System.out.println(response);
    }
}