package com.scheduling.system.Repository.repositories;

import com.scheduling.system.Repository.models.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Rafael Encinas.
 */
public interface StudentRepository extends CrudRepository<Student, Integer>{}
