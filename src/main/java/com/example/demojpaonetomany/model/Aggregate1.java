package com.example.demojpaonetomany.model;

import static javax.persistence.CascadeType.ALL;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "aggregate1")
public class Aggregate1 {

    @Id
    @GeneratedValue
    private Long                   id;
    @Column
    private String                 something;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "aggregate1Id", cascade = { ALL }, orphanRemoval = true)
    private final Set<Association> associations = new HashSet<>();

    public Aggregate1(String something) {
        this.something = something;
    }

    public boolean hasAssociation(Aggregate2 aggregate2) {
        return findAssociation(aggregate2).isPresent();
    }

    public Set<Association> getAssociations() {
        return Collections.unmodifiableSet(associations);
    }

    public void addAssociation(Aggregate2 aggregate2, String associationValue) {
        if (findAssociation(aggregate2).isPresent()) {
            throw new IllegalStateException("Association between aggregate1 " + id + " and aggregate2 "
                    + aggregate2.getId() + " already exists");
        }
        associations.add(new Association(id, aggregate2.getId(), associationValue));
    }

    public void updateAssociation(Aggregate2 aggregate2, String associationValue) {
        Optional<Association> association = findAssociation(aggregate2);
        if (!association.isPresent()) {
            throw new IllegalStateException("Association between aggregate1 " + id + " and aggregate2 "
                    + aggregate2.getId() + " does not exist");
        }
        associations.remove(association.get());
        associations.add(association.get().withNewValue(associationValue));
    }

    public void removeAssociation(Aggregate2 aggregate2) {
        Optional<Association> association = findAssociation(aggregate2);
        association.ifPresent(a -> this.associations.remove(a));
    }

    private Optional<Association> findAssociation(Aggregate2 aggregate2) {
        return associations.stream().filter(a -> a.getAggregate2Id().equals(aggregate2.getId())).findFirst();
    }

}
