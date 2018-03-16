package com.scheduling.system.DAL.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Rafael Encinas.
 */

@Entity
public class Student {

    private Integer id;
    private String name;
    private String last_name;
    private Set<Class> classes;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_code", referencedColumnName = "code"))
    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }
}
