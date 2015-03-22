package com.coupondunia.entity;

import com.coupondunia.bo.enums.Type;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roushan on 14/3/15.
 */
@MappedSuperclass
public abstract class Player extends LookupItem{

    @Column
    String description;

    @Column
    Date dob;

    @Column(nullable = false)
    Long price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(String dob) {
        try {
            this.dob = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        } catch (ParseException e) {
            this.dob = null;
        }
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
        sb.append("Player{description=").append(description).append(", dob=")
                .append(dob).append(", price=").append(price).append("}");
        return sb.toString();
    }
}

