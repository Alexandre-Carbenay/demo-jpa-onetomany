package com.example.demojpaonetomany.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demojpaonetomany.model.Aggregate1;

public interface Aggregate1JpaRepository extends JpaRepository<Aggregate1, Long> {

}
