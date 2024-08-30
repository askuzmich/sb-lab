package com.example.demo.endpoints.subEntity;

import com.example.demo.endpoints.headObject.HeadObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class SubEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String description;

    private String imgUrl;

    @ManyToOne
    private HeadObject owner;


    public SubEntity() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HeadObject getOwner() {
        return owner;
    }

    public void setOwner(HeadObject owner) {
        this.owner = owner;
    }
}
