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
import java.util.Map;

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
     * @return An studentDto
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto result = studentService.createStudent(studentDto);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all students.
     *
     * @return An an list of studentsDto object anotherwise null.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllStudents() {
        List<StudentDto> result = studentService.getAllStudents();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Adds a class to student.
     *
     * @param studentId An identifier of student.
     * @param code An identifier of class.
     * @return An an Student with your respective class anotherwise null.
     */
    @RequestMapping(method = RequestMethod.POST, path="/{studentId}/classes/{code}", produces = "application/json")
    public ResponseEntity<?> AddClassToStudent(@PathVariable int studentId,@PathVariable String code ) {
        StudentDto result = studentService.addStudentToClass(studentId, code);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all class by studentID.
     *
     * @param studentId An identifier of student.
     * @return An an list of ClassDtos object anotherwise null.
     */
    @RequestMapping(method = RequestMethod.GET, path="/{studentId}/classes", produces = "application/json")
    public ResponseEntity<?> getClassesByStudentId(@PathVariable int studentId) {
        List<ClassDto> result = studentService.getClassesByStudentId(studentId);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Delets a student.
     *
     * @param studentId An identifier of student.
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, path="/{studentId}", produces = "application/json")
    public ResponseEntity<?> deleteStudentById(@PathVariable int studentId) {
        boolean result = studentService.deleteStudent(studentId);
        if(result){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Updates a student.
     *
     * @param studentId An identifier of student.
     * @param studentDto An object
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, path="/{studentId}", produces = "application/json")
    public ResponseEntity<?> deleteStudentById(@PathVariable int studentId, @RequestBody StudentDto studentDto) {
        StudentDto result = studentService.updateStudent(studentId, studentDto);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Search a student
     *
     * @param request An object
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/search",produces = "application/json")
    public ResponseEntity<?> search(HttpServletRequest request) throws Exception {
        String queryString = request.getQueryString();
        Map<String, List<String>> listParemeters = Convert.StringToMap(queryString);
        List<StudentDto> studentDtos = studentService.searchBy(listParemeters.get("name").get(0), listParemeters.get("last_name").get(0));
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }
}
