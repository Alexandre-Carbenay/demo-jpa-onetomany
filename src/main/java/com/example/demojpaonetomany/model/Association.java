package com.example.demojpaonetomany.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Entity
@Table(name = "association")
@IdClass(AssociationId.class)
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "aggregate1_id")
    private final Long        aggregate1Id;
    @Id
    @Column(name = "aggregate2_id")
    private final Long        aggregate2Id;
    @Column(name = "association_value")
    private final String      associationValue;

    public Association withNewValue(String associationValue) {
        return new Association(aggregate1Id, aggregate2Id, associationValue);
    }

}
