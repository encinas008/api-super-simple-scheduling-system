package com.scheduling.system.Rest.controllers;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @throws Exception An problem in the creation process.
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createClass(@RequestBody ClassDto classDto) throws Exception {
        ClassDto result = classService.createClass(classDto);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets all classes.
     *
     * @return An an list of classesDto object anotherwise null.
     * @throws Exception An problem to get all classes process.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllClasses() throws Exception {
        List<ClassDto> result = classService.getAllClasses();
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
