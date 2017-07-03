package org.baeldung.spring.data.couchbase.service;

import java.util.List;

import org.baeldung.spring.data.couchbase.model.Student;

interface StudentService {

    Student findOne(String id);

    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    void create(Student student);

    void update(Student student);

    void delete(Student student);
}
