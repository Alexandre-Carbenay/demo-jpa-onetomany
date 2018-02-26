package com.example.demojpaonetomany.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class AssociationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long        aggregate1Id;
    private final Long        aggregate2Id;

}
