package com.sdc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sdc.model.Student;
import com.sdc.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return this.studentRepository.save(student);
	}

	public Optional<Student> findById(Integer id) {
		return this.studentRepository.findById(id);
	}

	public List<Student> findAllStudents() {
		return this.studentRepository.findAll();
	}

	public Boolean existById(Integer id) {
		return this.studentRepository.existsById(id);
	}

}
