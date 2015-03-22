package com.coupondunia.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by roushan on 15/3/15.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Table(name = "fielding_info")
@Entity
@XmlRootElement
public class FieldingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fielding_rank", unique = true)
    Integer fieldingRank;

    @Column(name = "wicket_keeping_rank", unique = true)
    Integer wicketKeepingRank;

    @Column(name = "fielding_stats")
    String fieldingStats; // better way to have this would have been creating a clob for class FieldingStats and persisting
    // the whole information in mysql in XML format. Could write a converter type with deep copy
    // for MySQL. That would help because new stats keep getting innovated in modern day cricket. However, not doing that because for this assignment purpose, we are not using that many details.

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

    public Integer getFieldingRank() {
        return fieldingRank;
    }

    public void setFieldingRank(Integer fieldingRank) {
        this.fieldingRank = fieldingRank;
    }

    public Integer getWicketKeepingRank() {
        return wicketKeepingRank;
    }

    public void setWicketKeepingRank(Integer wicketKeepingRank) {
        this.wicketKeepingRank = wicketKeepingRank;
    }

    public String getFieldingStats() {
        return fieldingStats;
    }

    public void setFieldingStats(String fieldingStats) {
        this.fieldingStats = fieldingStats;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FieldingInfo{id=").append(id).append(", fieldingRank=").append(fieldingRank).append(", wicketKeepingRank=").append(wicketKeepingRank)
                .append(", fieldingStats=").append(fieldingStats).append(", createdOn=").append(createdOn).append(", updatedOn=").append(updatedOn).append("}");
        return sb.toString();
    }
}
