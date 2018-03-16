package com.scheduling.system.Domain.parsers;

import com.scheduling.system.Domain.dtos.ClassDto;
import com.scheduling.system.DAL.models.Class;
import org.springframework.stereotype.Service;

/**
 * Created by Rafael Encinas.
 */
@Service
public class ClassParser implements IParser<Class, ClassDto>{

    @Override
    public ClassDto parserEntityToDto(Class entity) {
        ClassDto classDto = new ClassDto();
        classDto.setCode(entity.getCode());
        classDto.setTitle(entity.getTitle());
        classDto.setDescription(entity.getDescription());

        return classDto;
    }

    @Override
    public Class parserDtoToEntity(ClassDto dto) {
        Class _class = new Class();
        _class.setCode(dto.getCode());
        _class.setTitle(dto.getTitle());
        _class.setDescription(dto.getDescription());

        return _class;
    }
}
