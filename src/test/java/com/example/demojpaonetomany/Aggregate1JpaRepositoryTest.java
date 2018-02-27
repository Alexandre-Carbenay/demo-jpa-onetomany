package com.example.demojpaonetomany;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demojpaonetomany.model.Aggregate1;
import com.example.demojpaonetomany.model.Aggregate2;
import com.example.demojpaonetomany.persistence.Aggregate1JpaRepository;
import com.example.demojpaonetomany.persistence.Aggregate2JpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Aggregate1JpaRepositoryTest {

    @Autowired
    private Aggregate1JpaRepository aggregate1Repository;
    @Autowired
    private Aggregate2JpaRepository aggregate2Repository;

    @Test
    public void saveAggregate1WithNewAssociations() {
        long aggregate1Id = 1L;
        Aggregate1 aggregate1 = aggregate1Repository.findOne(aggregate1Id);
        assumeTrue("Unknown aggregate1 with id " + aggregate1Id, aggregate1 != null);
        long aggregate2Id = 3L;
        Aggregate2 aggregate2 = aggregate2Repository.findOne(aggregate2Id);
        assumeTrue("Unknown aggregate2 with id " + aggregate2Id, aggregate2 != null);

        aggregate1.addAssociation(aggregate2, "test association");
        Aggregate1 savedAggregate1 = aggregate1Repository.save(aggregate1);
        assertThat(savedAggregate1.hasAssociation(aggregate2)).isTrue();
    }

    @Test
    public void saveAggregate1RemoveExistingAssociation() {
        long aggregate1Id = 1L;
        Aggregate1 aggregate1 = aggregate1Repository.findOne(aggregate1Id);
        assumeTrue("Unknown aggregate1 with id " + aggregate1Id, aggregate1 != null);
        long aggregate2Id = 2L;
        Aggregate2 aggregate2 = aggregate2Repository.findOne(aggregate2Id);
        assumeTrue("Unknown aggregate2 with id " + aggregate2Id, aggregate2 != null);

        aggregate1.removeAssociation(aggregate2);
        Aggregate1 savedAggregate1 = aggregate1Repository.save(aggregate1);
        assertThat(savedAggregate1.hasAssociation(aggregate2)).isFalse();
    }

}
