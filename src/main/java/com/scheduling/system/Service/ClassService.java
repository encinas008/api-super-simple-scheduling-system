package com.scheduling.system.Service;

import com.scheduling.system.DAL.models.Student;
import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Domain.parsers.IParser;
import com.scheduling.system.DAL.models.Class;
import com.scheduling.system.DAL.repositories.ClassRepository;
import com.scheduling.system.Domain.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafael Encinas.
 */
@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    IParser<Class, ClassDto> parserClass;

    @Autowired
    IParser<Student, StudentDto> parserStudent;


    /**
     * Creates a new Class.
     *
     * @param classDto An object that contain information about a Class.
     * @return the classDto updated.
     */
    public ClassDto createClass(ClassDto classDto) {
        Class _class = parserClass.parserDtoToEntity(classDto);
        Class result = classRepository.save(_class);

        if(result != null){
            return classDto;
        }
        return null;
    }


    /**
     * Gets all Classes.
     *
     * @return the a list of classDto.
     */
    public List<ClassDto> getAllClasses() {
        List<ClassDto> classDtos = new ArrayList<>();
        Iterable<Class> classes = classRepository.findAll();
        for (Class _class: classes) {
            ClassDto classDto = parserClass.parserEntityToDto(_class);
            classDtos.add(classDto);
        }

        return classDtos;
    }

    /**
     * Deletes a class.
     *
     * @param code An identifier of class.
     * @return a boolean if is removed TRUE another wise FALSE.
     */
    public boolean deleteClass(String code)  {
        if(classRepository.findById(code).isPresent()) {
            Class _class = classRepository.findById(code).get();
            classRepository.delete(_class);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates a class.
     *
     * @param code An identifier of class.
     * @param classDto An object that contain information about of a Class.
     * @return A ClassDto
     */
    public ClassDto updateClass(String code, ClassDto classDto)  {
        if(classRepository.findById(code).isPresent()) {
            Class _class = classRepository.findById(code).get();
            _class.setTitle(classDto.getTitle());
            _class.setDescription(classDto.getDescription());

            Class classSaved = classRepository.save(_class);

            return parserClass.parserEntityToDto(classSaved);
        } else {
            return null;
        }
    }

    /**
     * Gets all Students by code of class.
     *
     * @param code An identifier of Class.
     * @return A list of Students.
     */
    public List<StudentDto> getStudentsByClassId(String code) {
        List<StudentDto> studentDtos = new ArrayList<>();
        if(classRepository.findById(code).isPresent()) {
            Class _class = classRepository.findById(code).get();
            Iterable<Student> students =  _class.getStudents();
            for (Student student: students) {
                StudentDto studentDto = parserStudent.parserEntityToDto(student);
                studentDtos.add(studentDto);
            }
        }

        return studentDtos;
    }

    /**
     * Search Classes by code or title or description.
     *
     * @param queryString An queryString of the request.
     * @return An list of classDtos.
     */
    public List<ClassDto> searchBy(String queryString) {
        List<ClassDto> classDtos = new ArrayList<>();
        String code = null;
        String title = null;
        String description = null;

        if(queryString != null) {
            Map<String, List<String>> listParemeters = Convert.StringToMap(queryString);
            code = listParemeters.get("code") != null ? listParemeters.get("code").get(0) : null;
            title = listParemeters.get("title") != null ? listParemeters.get("title").get(0) : null;
            description = listParemeters.get("description") != null ? listParemeters.get("description").get(0) : null;
        }

        if(code != null || title != null || description != null) {
            List<Class> classes = classRepository.searchClasses(code, title, description);
            for (Class _class: classes) {
                ClassDto classDto = parserClass.parserEntityToDto(_class);
                classDtos.add(classDto);
            }
        }

        return classDtos;
    }
}
