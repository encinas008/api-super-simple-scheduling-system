package com.scheduling.system.Rest.controllers;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Domain.utils.Convert;
import com.scheduling.system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rafael Encinas.
 */
@RestController
@RequestMapping(value="/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * Creates an student.
     *
     * @param studentDto An object that contain information about of student.
     * @return An object that contain studentDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto result = studentService.createStudent(studentDto);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all students.
     *
     * @return An object that contain a list of studentDto and a status.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllStudents() {
        List<StudentDto> result = studentService.getAllStudents();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes a student.
     *
     * @param studentId An identifier of student.
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, path="/{studentId}", produces = "application/json")
    public ResponseEntity<?> deleteStudentById(@PathVariable int studentId) {
        boolean result = studentService.deleteStudent(studentId);
        if(result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    /**
     * Updates a student.
     *
     * @param studentId An identifier of student.
     * @param studentDto An object
     * @return An object that contain a list of studentDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.PUT, path="/{studentId}", produces = "application/json")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody StudentDto studentDto) {
        StudentDto result = studentService.updateStudent(studentId, studentDto);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Adds a student to class.
     *
     * @param studentId An identifier of student.
     * @param code An identifier of class.
     * @return An object that contain a studentDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.POST, path="/{studentId}/classes/{code}", produces = "application/json")
    public ResponseEntity<?> AddStudentToClass(@PathVariable int studentId,@PathVariable String code ) {
        StudentDto result = studentService.addStudentToClass(studentId, code);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all classes by studentID.
     *
     * @param studentId An identifier of student.
     * @return An object that contain a list of studentDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.GET, path="/{studentId}/classes", produces = "application/json")
    public ResponseEntity<?> getClassesByStudentId(@PathVariable int studentId) {
        List<ClassDto> result = studentService.getClassesByStudentId(studentId);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Search a students.
     *
     * @param request An HttpServletRequest object
     * @return An object that contain a list of studentDto and a status.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/search",produces = "application/json")
    public ResponseEntity<?> search(HttpServletRequest request) {
        String queryString = request.getQueryString();
        List<StudentDto> studentDtos = studentService.searchBy(queryString);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }
}
