package com.scheduling.system.Service;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.Domain.parsers.IParser;
import com.scheduling.system.Repository.models.Class;
import com.scheduling.system.Repository.repositories.ClassRepository;
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
    IParser<Class, ClassDto> iParser;

    public ClassDto createClass(ClassDto classDto) {
        Class _class = iParser.parserDtoToEntity(classDto);
        Class result = classRepository.save(_class);

        if(result != null){
            return classDto;
        }
        return null;
    }

    public List<ClassDto> getAllClasses(){
        List<ClassDto> classDtos = new ArrayList<>();
        Iterable<Class> classes = classRepository.findAll();
        for (Class _class: classes) {
            ClassDto classDto = iParser.parserEntityToDto(_class);
            classDtos.add(classDto);
        }

        return classDtos;
    }
}
