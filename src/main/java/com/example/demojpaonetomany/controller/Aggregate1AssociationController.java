package com.example.demojpaonetomany.controller;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demojpaonetomany.model.AddAssociation;
import com.example.demojpaonetomany.model.Aggregate1;
import com.example.demojpaonetomany.model.Aggregate2;
import com.example.demojpaonetomany.persistence.Aggregate1JpaRepository;
import com.example.demojpaonetomany.persistence.Aggregate2JpaRepository;

@RestController
@RequestMapping(value = "/aggregate1/{aggregate1}/associations/{aggregate2}",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class Aggregate1AssociationController {

    @Autowired
    private Aggregate1JpaRepository aggregate1Repository;
    @Autowired
    private Aggregate2JpaRepository aggregate2Repository;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAssociation(@PathVariable(name = "aggregate1") Long aggregate1Id,
            @PathVariable(name = "aggregate2") Long aggregate2Id, @RequestBody AddAssociation command) {
        Aggregate1 aggregate1 = aggregate1Repository.findOne(aggregate1Id);
        Aggregate2 aggregate2 = aggregate2Repository.findOne(aggregate2Id);
        notNull(aggregate1, "Cannot create association from unknown aggregate1 " + aggregate1Id);
        notNull(aggregate2, "Cannot create association to unknown aggregate2 " + aggregate2Id);
        if (!aggregate1.hasAssociation(aggregate2)) {
            aggregate1.addAssociation(aggregate2, command.getAssociationValue());
        } else {
            aggregate1.updateAssociation(aggregate2, command.getAssociationValue());
        }

        aggregate1Repository.save(aggregate1);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssociation(@PathVariable(name = "aggregate1") Long aggregate1Id,
            @PathVariable(name = "aggregate2") Long aggregate2Id) {
        Aggregate1 aggregate1 = aggregate1Repository.findOne(aggregate1Id);
        Aggregate2 aggregate2 = aggregate2Repository.findOne(aggregate2Id);
        notNull(aggregate1, "Cannot delete association from unknown aggregate1 " + aggregate1Id);
        notNull(aggregate2, "Cannot delete association to unknown aggregate2 " + aggregate2Id);
        aggregate1.removeAssociation(aggregate2);
        aggregate1Repository.save(aggregate1);
    }

}
