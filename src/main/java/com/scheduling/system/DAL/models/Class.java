package com.scheduling.system.DAL.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Rafael Encinas.
 */
@Entity
public class Class {
    public String code;
    public String title;
    public String description;
    private Set<Student> students;


    @Id
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "classes")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
