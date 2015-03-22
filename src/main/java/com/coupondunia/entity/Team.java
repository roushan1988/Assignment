package com.coupondunia.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.MapKey;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Table(name = "team")
@Entity
@XmlRootElement
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @JoinColumn(name = "captain_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    CricketPlayer captain;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "team_id")
    List<TeamCricketPlayer> squad;

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdOn;

    @Column(name = "updated_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CricketPlayer getCaptain() {
        return captain;
    }

    public void setCaptain(CricketPlayer captain) {
        this.captain = captain;
    }

    public List<TeamCricketPlayer> getSquad() {
        return squad;
    }

    public void setSquad(List<TeamCricketPlayer> squad) {
        this.squad = squad;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
