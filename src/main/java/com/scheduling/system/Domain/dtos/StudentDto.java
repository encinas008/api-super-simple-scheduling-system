package com.scheduling.system.Domain.dtos;

/**
 *
 * This StudentDto class is for define attributes of input for a Student.
 *
 * Created by Rafael Encinas.
 */
public class StudentDto {
    private Integer studentId;
    private String name;
    private String last_name;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
}
