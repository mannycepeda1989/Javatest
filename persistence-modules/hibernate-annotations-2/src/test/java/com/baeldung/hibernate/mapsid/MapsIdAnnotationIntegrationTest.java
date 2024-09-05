package com.baeldung.hibernate.mapsid;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapsIdAnnotationIntegrationTest {

    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        session = HibernateConfigUtils.sessionFactory()
            .openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        transaction.rollback();
        session.close();
    }

    @Test
    void givenPersonEntityWithIdentifier_whenAddingAddress_thenPersistWithSameIdentifier() {
        // Given person with identifier 3
        int personId = 3;
        Person person = new Person();
        person.setId(personId);
        person.setFirstName("Azhrioun");
        person.setLastName("Abderrahim");
        session.persist(person);

        // Address without specifying the identifier
        Address address = new Address();
        address.setStreet("7 avenue berlin");
        address.setCity("Tamassint");
        address.setZipode(13000);
        address.setPerson(person);

        // When
        session.persist(address);
        Address persistedAddress = session.find(Address.class, personId);

        // Then address shares the same identifier as person
        assertThat(persistedAddress.getId()).isEqualTo(personId);
    }

}
