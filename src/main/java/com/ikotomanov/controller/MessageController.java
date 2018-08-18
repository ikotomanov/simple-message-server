package com.ikotomanov.controller;

import com.ikotomanov.model.ResultType;
import com.ikotomanov.service.MessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {
    @NonNull
    MessageService messageService;

    @RequestMapping(value = "/messages/{type}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> handleMessage(@PathVariable("type") String type, @RequestBody String payload) {

        HttpStatus httpStatus;
        ResultType result = messageService.processMessage(type, payload);
        if (ResultType.VALIDATION_FAILED.equals(result))
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        else
            httpStatus = HttpStatus.CREATED;

        return ResponseEntity.status(httpStatus).body(null);

    }
}
