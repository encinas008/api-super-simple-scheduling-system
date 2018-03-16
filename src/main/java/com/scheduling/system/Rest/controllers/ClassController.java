package com.scheduling.system.Rest.controllers;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Domain.utils.Convert;
import com.scheduling.system.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    /**
     * Gets all students by identifier class.
     *
     * @param code An identifier of class.
     * @return An an list of objects studentDto another wise null.
     * @throws Exception An problem to get all students process.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{code}/students", produces = "application/json")
    public ResponseEntity<?> getAllStudentsByClassId(@PathVariable String code) throws Exception {
        List<StudentDto> result = classService.getStudentsByClassId(code);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Search a class by code or title or description.
     *
     * @param request An object
     * @return
     * @throws Exception An problem to get all class process.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/search",produces = "application/json")
    public ResponseEntity<?> search(HttpServletRequest request) throws Exception {
        String queryString = request.getQueryString();
        Map<String, List<String>> listParemeters = Convert.StringToMap(queryString);
        List<ClassDto> classDtos = classService.searchBy(listParemeters.get("code").get(0), listParemeters.get("title").get(0), listParemeters.get("description").get(0));
        return new ResponseEntity<>(classDtos, HttpStatus.OK);
    }
}
