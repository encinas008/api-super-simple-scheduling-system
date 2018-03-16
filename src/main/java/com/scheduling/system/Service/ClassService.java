package com.scheduling.system.Service;

import com.scheduling.system.DAL.models.Student;
import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Domain.parsers.IParser;
import com.scheduling.system.DAL.models.Class;
import com.scheduling.system.DAL.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public boolean deleteStudent(String code)  {
        Class _class = classRepository.findById(code).get();
        if(_class == null) {
            return false;
        }
        classRepository.delete(_class);
        return true;
    }

    /**
     * Updates a class.
     *
     * @param code An identifier of class.
     * @param classDto An object that contain information about of a Class.
     * @return A ClassDto
     */
    public ClassDto updateStudent(String code, ClassDto classDto)  {
        Class _class = classRepository.findById(code).get();
        _class.setTitle(classDto.getTitle());
        _class.setDescription(classDto.getDescription());
        Class classSaved = classRepository.save(_class);

        if(classSaved != null) {
            return parserClass.parserEntityToDto(classSaved);
        }
        return null;
    }

    /**
     * Gets all Students by ClassId.
     *
     * @param code An identifier of Class.
     * @return A list of Students.
     */
    public List<StudentDto> getStudentsByClassId(String code) {
        List<StudentDto> studentDtos = new ArrayList<>();
        Class _class = classRepository.findById(code).get();
        Iterable<Student> students =  _class.getStudents();
        for (Student student: students) {
            StudentDto studentDto = parserStudent.parserEntityToDto(student);
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    /**
     * Search Class by code or title or description.
     *
     * @param code An indentifier of class.
     * @param title An title of class.
     * @param description An breve description of class.
     * @return An list of class.
     */
    public List<ClassDto> searchBy(String code, String title, String description) {
        List<ClassDto> classDtos = new ArrayList<>();
        List<Class> classes = classRepository.searchClasses(code, title, description);
        for (Class _class: classes) {
            ClassDto classDto = parserClass.parserEntityToDto(_class);
            classDtos.add(classDto);
        }
        return classDtos;
    }
}
