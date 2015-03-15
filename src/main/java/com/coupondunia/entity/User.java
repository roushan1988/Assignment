package com.coupondunia.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.MapKey;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

/**
 * Created by roushan on 15/3/15.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String email;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    @XmlTransient
    String password;

    @Column(name = "starting_amount", nullable = false)
    Long startingAmount;

    @Column(name = "balance_amount", nullable = false)
    Long balanceAmount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", unique = true)
    @Fetch(FetchMode.SELECT)
    Team team;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(Long startingAmount) {
        this.startingAmount = startingAmount;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{id=").append(id).append(", name=").append(name).append(", email=").append(email)
                .append(", username=").append(username).append(", startingAmount=").append(startingAmount)
                .append(", balanceAmount=").append(balanceAmount).append(", team=").append(team)
                .append(", createdOn=").append(createdOn).append(", updatedOn=").append(updatedOn)
                .append("}");
        return sb.toString();
    }
}
