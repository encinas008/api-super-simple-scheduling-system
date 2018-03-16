package com.scheduling.system.DAL.repositories;
import com.scheduling.system.DAL.models.Class;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rafael Encinas.
 */
public interface ClassRepository extends CrudRepository<Class, String> {
    @Query("select c from Class c where c.code LIKE CONCAT('%',:code,'%') or c.title LIKE CONCAT('%',:title,'%') or c.description LIKE CONCAT('%',:description,'%')")
    List<Class> searchClasses(@Param("code") String code, @Param("title") String title, @Param("description") String description);
}
