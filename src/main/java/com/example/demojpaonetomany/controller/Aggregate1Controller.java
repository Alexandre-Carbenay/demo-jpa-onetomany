package com.example.demojpaonetomany.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demojpaonetomany.model.Aggregate1;
import com.example.demojpaonetomany.model.CreateAggregate1;
import com.example.demojpaonetomany.persistence.Aggregate1JpaRepository;

@RestController
@RequestMapping(value = "/aggregate1", produces = MediaType.APPLICATION_JSON_VALUE)
public class Aggregate1Controller {

    @Autowired
    private Aggregate1JpaRepository repository;

    private Method                  aggregate1DetailMethod;

    @PostConstruct
    public void init() throws NoSuchMethodException {
        aggregate1DetailMethod = Aggregate1Controller.class.getMethod("aggregate", Long.class);
    }

    @GetMapping
    public List<Aggregate1> aggregates() {
        return repository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders createAggregate(@RequestBody CreateAggregate1 command) {
        Aggregate1 aggregate1 = new Aggregate1(command.getSomething());
        repository.save(aggregate1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(linkTo(aggregate1DetailMethod, aggregate1.getId()).toUri());
        return httpHeaders;
    }

    @GetMapping("/{id}")
    public Aggregate1 aggregate(@PathVariable(name = "id") Long id) {
        return repository.findOne(id);
    }

}
