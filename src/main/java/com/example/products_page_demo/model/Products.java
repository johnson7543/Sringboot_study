package com.example.products_page_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
// @Entity的Bean是告訴Spring這是data model
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "code")
    @JsonProperty("code")
    private String code;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @Column(name = "description", length = 10000)
    @JsonProperty("description")
    private String description;

    public Products() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Products{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
