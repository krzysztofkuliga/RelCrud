package com.projects.example.RelCrud.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private boolean published;

    public Company(String name, String description, boolean published) {
        this.name = name;
        this.description = description;
        this.published = published;
    }
}
