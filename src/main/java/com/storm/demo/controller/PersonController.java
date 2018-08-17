package com.storm.demo.controller;

import com.storm.demo.model.Person;
import com.storm.demo.service.PersonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonController {
    @NonNull
    PersonService persons;

    @GetMapping("/johnsmith")
    public Person hello() {
        return persons.johnSmith();
    }

    @PostMapping("/hello")
    public String postHello(@RequestBody final Person person) {
        return persons.hello(person);
    }
}
