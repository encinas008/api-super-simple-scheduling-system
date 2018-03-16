package com.scheduling.system.Domain.dtos;

/**
 *
 * This ClassDto class is for define attributes of input for a Class.
 *
 * Created by Rafael Encinas.
 */
public class ClassDto {
    private String code;
    private String title;
    private String description;

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
}
