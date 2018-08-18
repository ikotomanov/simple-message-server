package com.ikotomanov.service;

import com.ikotomanov.model.ResultType;

public interface MessageService {
    ResultType processMessage(String type, String content);
}
