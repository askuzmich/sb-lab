package com.example.demo.endpoints.headObject;

import com.example.demo.endpoints.subEntity.SubEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HeadObject implements Serializable {
    @Id
//    @SequenceGenerator(name = "head_object_sequence", sequenceName = "head_object_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "head_object_sequence")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "owner"
    )
    private List<SubEntity> subEntities = new ArrayList<>();


    public HeadObject() {
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

    public List<SubEntity> getSubEntities() {
        return subEntities;
    }

    public void setSubEntities(List<SubEntity> subEntities) {
        this.subEntities = subEntities;
    }

    public void addSubEntity(SubEntity subEntity) {
        subEntity.setOwner(this);
        this.subEntities.add(subEntity);
    }

    public Integer getNumberOfSE() {
        return this.subEntities.size();
    }

    public void removeAllSubEntities() {
        this.subEntities
            .stream()
            .forEach((subEntity) -> {
                subEntity.setOwner(null);
            });

        this.subEntities = null;
    }

    public void removeSubEntity(SubEntity subEntity) {
        subEntity.setOwner(null);
        this.subEntities.remove(subEntity);
    }
}
