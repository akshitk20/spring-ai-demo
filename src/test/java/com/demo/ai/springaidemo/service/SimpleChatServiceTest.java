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

}