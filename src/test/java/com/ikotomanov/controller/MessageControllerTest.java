package com.ikotomanov.controller;

import com.ikotomanov.SimpleMessageServer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleMessageServer.class)
@WebAppConfiguration
public class MessageControllerTest {

    private static final String SEND_TEXT_URL = "/messages/send_text";
    private static final String SEND_EMOTION_URL = "/messages/send_emotion";
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void sendValidTextMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_TEXT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("Valid text message."))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void sendTooLongTextMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_TEXT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("This message is way too%s long!", StringUtils.repeat('o', 160))))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
    }

    @Test
    public void sendEmptyTextMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_TEXT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(StringUtils.EMPTY))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void sendValidEmotionMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_EMOTION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("101010"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void sendInvalidCharEmotionMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_EMOTION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("-10"))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
    }

    @Test
    public void sendTooLongEmotionMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_EMOTION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("12345678900"))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
    }

    @Test
    public void sendTooShortEmotionMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_EMOTION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("1"))
                .andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
    }

    @Test
    public void sendEmptyEmotionMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(SEND_EMOTION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(StringUtils.EMPTY))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
