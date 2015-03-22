package com.coupondunia.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by roushan on 15/3/15.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Table(name = "batting_info")
@Entity
@XmlRootElement
public class BattingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "batting_rank", unique = true)
    Integer battingRank;

    @Column(name = "batting_stats")
    String battingStats; // better way to have this would have been creating a clob for class BattingStats and persisting
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

    public Integer getBattingRank() {
        return battingRank;
    }

    public void setBattingRank(Integer battingRank) {
        this.battingRank = battingRank;
    }

    public String getBattingStats() {
        return battingStats;
    }

    public void setBattingStats(String battingStats) {
        this.battingStats = battingStats;
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
        sb.append("BattingInfo{id=").append(id).append(", battingRank=").append(battingRank).append(", battingStats=").append(battingStats)
                .append(", createdOn=").append(createdOn).append(", updatedOn=").append(updatedOn).append("}");
        return sb.toString();
    }

}
