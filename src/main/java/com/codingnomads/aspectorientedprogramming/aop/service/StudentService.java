/* CodingNomads (C)2024 */
package com.codingnomads.aspectorientedprogramming.aop.service;

import com.codingnomads.aspectorientedprogramming.aop.aspect.Loggable;
import com.codingnomads.aspectorientedprogramming.aop.aspect.MuckyDuck;
import com.codingnomads.aspectorientedprogramming.aop.model.Student;
import com.codingnomads.aspectorientedprogramming.aop.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // @TrackMethodExecutionTime
    // @Loggable
    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    // @TrackMethodExecutionTime
    @Loggable
    public List<Student> saveAllStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @MuckyDuck
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @MuckyDuck
    public Student saveStudent(String name, String email) {
        return studentRepository.save(Student.builder().email(email).name(name).build());
    }
}
