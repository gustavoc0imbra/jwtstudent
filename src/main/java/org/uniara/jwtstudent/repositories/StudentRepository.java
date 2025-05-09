package org.uniara.jwtstudent.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.uniara.jwtstudent.models.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
