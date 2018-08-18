package com.ikotomanov.service;

import com.ikotomanov.SimpleMessageServer;
import com.ikotomanov.entity.PersistentMessage;
import com.ikotomanov.model.MessageType;
import com.ikotomanov.model.ResultType;
import com.ikotomanov.repository.MessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleMessageServer.class)
@WebAppConfiguration
public class MessageServiceTest {

    @Autowired
    private MessageService service;
    @Autowired
    private MessageRepository repository;

    @Test
    public void processTextMessageSucceed() {
        ResultType result = service.processMessage(MessageType.TEXT.getValue(), "This is text message");
        Assert.assertEquals(ResultType.SUCCESS, result);

        Optional<PersistentMessage> persistentMessage = repository.findById(1L);
        Assert.assertTrue(persistentMessage.isPresent());
        Assert.assertEquals(MessageType.TEXT, persistentMessage.get().getType());
        Assert.assertEquals("This is text message", persistentMessage.get().getPayload());
    }

    @Test
    public void processTextMessageFailed() {
        ResultType result = service.processMessage(MessageType.TEXT.getValue(), StringUtils.EMPTY);
        Assert.assertEquals(ResultType.VALIDATION_FAILED, result);
    }
}
