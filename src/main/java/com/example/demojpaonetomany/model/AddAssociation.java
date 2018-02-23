package com.example.demojpaonetomany.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddAssociation {

    private String associationValue;

    @JsonCreator
    public AddAssociation(@JsonProperty("associationValue") @NonNull String associationValue) {
        this.associationValue = associationValue;
    }

}
