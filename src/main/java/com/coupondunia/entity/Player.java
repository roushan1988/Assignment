package com.coupondunia.entity;

import com.coupondunia.bo.enums.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roushan on 14/3/15.
 */
@MappedSuperclass
public abstract class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column
    Date dob;

    @Column(nullable = false)
    Long price;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdOn;

    @Column(nullable = false)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!id.equals(player.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player{id=").append(id).append(", name=").append(name)
                .append(", description=").append(description).append(", dob=")
                .append(dob).append(", price=").append(price).append(", createdOn=")
                .append(createdOn).append(", updatedOn=").append(updatedOn).append("}");
        return sb.toString();
    }
}

