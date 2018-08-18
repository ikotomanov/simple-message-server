package com.ikotomanov.service;

import com.ikotomanov.entity.PersistentMessage;
import com.ikotomanov.model.Message;
import com.ikotomanov.model.MessageFactory;
import com.ikotomanov.model.ResultType;
import com.ikotomanov.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private Validator validator;
    @Autowired
    private MessageRepository repository;

    @Override
    public ResultType processMessage(String type, String content) {
        Message message = MessageFactory.createMessage(type);
        message.setContent(content);

        if (!validateMessage(message)) {
            return ResultType.VALIDATION_FAILED;
        }

        persistMessage(message);
        return ResultType.SUCCESS;
    }

    private boolean validateMessage(Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);
        return violations.isEmpty();
    }

    private void persistMessage(Message message) {
        PersistentMessage persistentMessage = new PersistentMessage();
        persistentMessage.setType(message.getType());
        persistentMessage.setPayload(message.getContent());

        repository.save(persistentMessage);

    }

}
