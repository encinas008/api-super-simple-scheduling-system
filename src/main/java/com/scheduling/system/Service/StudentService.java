package com.scheduling.system.Service;

import com.scheduling.system.DAL.models.Class;
import com.scheduling.system.DAL.repositories.ClassRepository;
import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.Domain.parsers.IParser;
import com.scheduling.system.DAL.models.Student;
import com.scheduling.system.DAL.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafael Encinas.
 */
@Service
public class StudentService {
    @Autowired
    IParser<Student, StudentDto> parserStudent;

    @Autowired
    IParser<Class, ClassDto> parserClass;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    /**
     * Creates a new Student.
     *
     * @param studentDto An object that contain information about a Student.
     * @return the studentDto updated.
     * @throws Exception
     */
    public StudentDto createStudent(StudentDto studentDto) throws Exception {
        Student student = parserStudent.parserDtoToEntity(studentDto);
        Student result = studentRepository.save(student);

        if(result != null) {
            return parserStudent.parserEntityToDto(result);
        }
        return null;
    }

    /**
     * Gets all Students.
     *
     * @return the a list of studentDto.
     * @throws Exception
     */
    public List<StudentDto> getAllStudents() throws Exception {
        List<StudentDto> studentDtos = new ArrayList<>();
        Iterable<Student> students = studentRepository.findAll();
        if(students != null) {
            for (Student student: students) {
                StudentDto studentDto = parserStudent.parserEntityToDto(student);
                studentDtos.add(studentDto);
            }
            return studentDtos;
        }
        return null;
    }

    /**
     * Deletes a student.
     *
     * @param studentId An identifier of student.
     * @return a boolean if is removed TRUE another wise FALSE.
     * @throws Exception
     */
    public boolean deleteStudent(int studentId) throws Exception {
        Student student = studentRepository.findById(studentId).get();
        if(student == null) {
            return false;
        }
        studentRepository.delete(student);
        return true;
    }

    /**
     * Updates a student.
     *
     * @param studentId An identifier of student.
     * @param studentDto An object that contain information about a Student.
     * @return A StudentDto
     * @throws Exception
     */
    public StudentDto updateStudent(int studentId, StudentDto studentDto) throws Exception {
        Student student = studentRepository.findById(studentId).get();
        student.setName(studentDto.getName());
        student.setLast_name(studentDto.getLastName());
        Student studentSaved = studentRepository.save(student);

        if(studentSaved != null) {
            return parserStudent.parserEntityToDto(studentSaved);
        }
        return null;
    }

    /**
     * Adds a student to class.
     *
     * @param studentId An identifier of student.
     * @param classCode An identifier of Class.
     * @return A StudentDto object.
     * @throws Exception
     */
    public StudentDto addStudentToClass(int studentId, String classCode) throws Exception {
        Class _class = classRepository.findById(classCode).get();
        Student student = studentRepository.findById(studentId).get();
        student.getClasses().add(_class);
        studentRepository.save(student);
        StudentDto studentDto = parserStudent.parserEntityToDto(student);
        return studentDto;
    }

    /**
     * Gets all class assignment to student.
     *
     * @param studentId An identifier of student.
     * @return A list of ClassDto.
     * @throws Exception
     */
    public List<ClassDto> getClassesByStudentId(int studentId) throws Exception {
        List<ClassDto> classDtos = new ArrayList<>();
        Student student = studentRepository.findById(studentId).get();
        Set<Class> classes = student.getClasses();
        for (Class _class: classes) {
            ClassDto classDto = parserClass.parserEntityToDto(_class);
            classDtos.add(classDto);
        }

        return classDtos;
    }

    /**
     * Search Student by name or last name.
     *
     * @param name An name of student
     * @param lastName An last name of student.
     * @return An list of students.
     * @throws Exception
     */
    public List<StudentDto> searchBy(String name, String lastName)  throws Exception {
        if(!name.isEmpty()) {
            List<StudentDto> studentDtos = new ArrayList<>();
            List<Student> students = studentRepository.searchStudents(name, lastName);
            for (Student student: students) {
                StudentDto studentDto = parserStudent.parserEntityToDto(student);
                studentDtos.add(studentDto);
            }
            return studentDtos;
        }
        return null;
    }
}
