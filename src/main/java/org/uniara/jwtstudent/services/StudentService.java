package org.uniara.jwtstudent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.jwtstudent.models.Student;
import org.uniara.jwtstudent.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }
}
