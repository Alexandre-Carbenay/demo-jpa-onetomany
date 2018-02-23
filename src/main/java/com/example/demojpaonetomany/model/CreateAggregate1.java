package com.example.demojpaonetomany.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreateAggregate1 {

    private final String something;

    @JsonCreator
    public CreateAggregate1(@JsonProperty("something") @NonNull String something) {
        this.something = something;
    }

}
