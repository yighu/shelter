package com.ffg.shelter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
public class CampStats implements Serializable {
    private Long id;
    private CampStat campStat;
    private int count;
    @JsonIgnoreProperties
    protected Set<Camp> camps;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campStats_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public CampStat getCampStat() {
        return campStat;
    }

    public void setCampStat(CampStat campStat) {
        this.campStat = campStat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @ManyToMany
    @JoinColumn(name = "campStats_id", updatable = false, insertable = false, nullable = false)
    public Set<Camp> getCamps() {
        return camps;
    }

    public void setCamps(Set<Camp> camps) {
        this.camps = camps;
    }
}
