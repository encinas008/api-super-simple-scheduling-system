package com.scheduling.system.Rest.controllers;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Service.ClassService;
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
@RequestMapping(value="/classes")
public class ClassController {
    @Autowired
    ClassService classService;

    /**
     * Creates an class.
     *
     * @param classDto An object that contain information about of class.
     * @return An classDto
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createClass(@Valid @RequestBody ClassDto classDto) {
        ClassDto result = classService.createClass(classDto);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all classes.
     *
     * @return An object that contain list of classDto and a status.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllClasses() {
        List<ClassDto> result = classService.getAllClasses();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes a class.
     *
     * @param code An identifier of class.
     * @return An object that contain TRUE if the class was deleted another wise FALSE and a status.
     */
    @RequestMapping(method = RequestMethod.DELETE, path="/{code}", produces = "application/json")
    public ResponseEntity<?> deleteClassById(@PathVariable String code) {
        boolean result = classService.deleteClass(code);
        if(result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    /**
     * Updates a class.
     *
     * @param code An identifier of class.
     * @param classDto An object that contain new info for update a class.
     * @return An object that contain classDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.PUT, path="/{code}", produces = "application/json")
    public ResponseEntity<?> updateClass(@PathVariable String code, @RequestBody ClassDto classDto) {
        ClassDto result = classService.updateClass(code, classDto);
        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }


    /**
     * Gets all students by identifier of class.
     *
     * @param code An identifier of class.
     * @return An object that contain list of studentDto or empty and a status.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{code}/students", produces = "application/json")
    public ResponseEntity<?> getAllStudentsByClassId(@PathVariable String code) {
        List<StudentDto> result = classService.getStudentsByClassId(code);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    /**
     * Search a class by code or title or description.
     *
     * @param request An HttpServletRequest object.
     * @return An object that contain classDto and a status.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/search",produces = "application/json")
    public ResponseEntity<?> search(HttpServletRequest request) {
        String queryString = request.getQueryString();
        List<ClassDto> classDtos = classService.searchBy(queryString);
        return new ResponseEntity<>(classDtos, HttpStatus.OK);
    }
}
