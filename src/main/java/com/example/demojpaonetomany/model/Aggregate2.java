package com.example.demojpaonetomany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "aggregate2")
public class Aggregate2 {

    @Id
    private Long   id;
    @Column
    private String value;

    public Aggregate2(String value) {
        this.value = value;
    }

}
