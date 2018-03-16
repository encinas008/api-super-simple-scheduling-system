package com.scheduling.system.Repository.models;

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
}
