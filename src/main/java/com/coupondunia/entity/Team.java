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
    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    CricketPlayer captain;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "team_player_mapping",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    List<CricketPlayer> squad;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdOn;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedOn;
}
