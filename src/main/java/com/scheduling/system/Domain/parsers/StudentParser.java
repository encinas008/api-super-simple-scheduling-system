package com.scheduling.system.Domain.parsers;

import com.scheduling.system.Domain.dtos.StudentDto;
import com.scheduling.system.DAL.models.Student;
import org.springframework.stereotype.Service;

/**
 * Created by jrafa Rafael Encinas.
 */
@Service
public class StudentParser implements IParser<Student, StudentDto> {
    @Override
    public StudentDto parserEntityToDto(Student entity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(entity.getId());
        studentDto.setLast_name(entity.getLast_name());
        studentDto.setName(entity.getName());

        return studentDto;
    }

    @Override
    public Student parserDtoToEntity(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setLast_name(dto.getLast_name());

        return student;
    }
}
