package com.scheduling.system.DAL.repositories;

import com.scheduling.system.DAL.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rafael Encinas.
 */
public interface StudentRepository extends CrudRepository<Student, Integer>{
    @Query("select s from Student s where s.name LIKE CONCAT('%',:name,'%') or s.last_name LIKE CONCAT('%',:last_name,'%')")
    List<Student> searchStudents(@Param("name") String name, @Param("last_name") String last_name);
}
