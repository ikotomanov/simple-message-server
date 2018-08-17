package com.storm.demo.service;

import com.storm.demo.model.Person;

public interface PersonService {
    Person johnSmith();
    String hello(Person person);
}
