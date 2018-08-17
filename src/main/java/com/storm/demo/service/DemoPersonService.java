package com.storm.demo.service;

import com.storm.demo.model.Person;
import org.springframework.stereotype.Service;

@Service
public class DemoPersonService implements PersonService {
    @Override
    public Person johnSmith() {
        final Person person = new Person();
        person.setFirstname("John");
        person.setLastname("Smith");
        return person;
    }

    @Override
    public String hello(Person person) {
        return "Hello " + person.getFirstname() + " " + person.getLastname() + "!";
    }
}
