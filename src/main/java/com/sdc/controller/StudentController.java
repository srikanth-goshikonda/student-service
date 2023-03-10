package com.sdc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdc.dto.StudentRequest;
import com.sdc.dto.StudentResponse;
import com.sdc.model.Student;
import com.sdc.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Tag(name = "Student API Endpoints")
public class StudentController {

	private final StudentService studentService;

	@GetMapping
	public List<StudentResponse> students() {
		List<Student> findAllStudents = this.studentService.findAllStudents();
		List<StudentResponse> list = findAllStudents.stream().map(x -> x.convertToStudentResponse()).toList();
		return list;
	}

	@GetMapping("{id}")
	public ResponseEntity<?> student(@PathVariable Integer id) {
		Optional<Student> student = this.studentService.findById(id);

		if (student.isPresent()) {
			return ResponseEntity.ok(student.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("{id}")
	public ResponseEntity<?> studentUpdate(@RequestBody @Valid StudentRequest request, @PathVariable Integer id) {

		Boolean existById = this.studentService.existById(id);
		if (existById) {

			Student student = request.convertToStudentEntity();
			student.setRollNumber(id);
			Student saveStudent = this.studentService.saveStudent(student);

			return new ResponseEntity<StudentResponse>(saveStudent.convertToStudentResponse(), HttpStatus.OK);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping()
	public ResponseEntity<StudentResponse> saveStudent(@RequestBody @Valid StudentRequest request) {

		StudentResponse studentResponse = studentService.saveStudent(request.convertToStudentEntity())
				.convertToStudentResponse();

		return new ResponseEntity<StudentResponse>(studentResponse, HttpStatus.CREATED);

	}
}
