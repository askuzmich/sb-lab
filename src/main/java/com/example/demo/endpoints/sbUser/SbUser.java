package com.example.demo.endpoints.sbUser;

import com.example.demo.endpoints.subEntity.SubEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SbUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "space separated roles are required")
    private String roles;

    @NotEmpty(message = "password is required")
    private String password;

    private boolean enabled;




    public SbUser() {
    }





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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
//    public List<SubEntity> getSubEntities() {
//        return subEntities;
//    }

//    public void setSubEntities(List<SubEntity> subEntities) {
//        this.subEntities = subEntities;
//    }
//
//    public void addSubEntity(SubEntity subEntity) {
//        //subEntity.setOwner(this);
//        this.subEntities.add(subEntity);
//    }

//    public Integer getNumberOfSE() {
//        return this.subEntities.size();
//    }

//    public void removeAllSubEntities() {
//        this.subEntities
//            .stream()
//            .forEach((subEntity) -> {
//                subEntity.setOwner(null);
//            });
//
//        this.subEntities = null;
//    }

//    public void removeSubEntity(SubEntity subEntity) {
//        subEntity.setOwner(null);
//        this.subEntities.remove(subEntity);
//    }
}
