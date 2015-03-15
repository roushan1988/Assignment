package com.coupondunia.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by roushan on 15/3/15.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Table(name = "bowling_info")
@Entity
@XmlRootElement
public class BowlingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    Integer bowlingRank;

    @Column
    String bowlingStats; // better way to have this would have been creating a clob for class BowlingStats and persisting
    // the whole information in mysql in XML format. Could write a converter type with deep copy
    // for MySQL. That would help because new stats keep getting innovated in modern day cricket. However, not doing that because for this assignment purpose, we are not using that many details.

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

    public Integer getBowlingRank() {
        return bowlingRank;
    }

    public void setBowlingRank(Integer bowlingRank) {
        this.bowlingRank = bowlingRank;
    }

    public String getBowlingStats() {
        return bowlingStats;
    }

    public void setBowlingStats(String bowlingStats) {
        this.bowlingStats = bowlingStats;
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
        sb.append("BattingInfo{id=").append(id).append(", bowlingRank=").append(bowlingRank).append(", bowlingStats=").append(bowlingStats)
                .append(", createdOn=").append(createdOn).append(", updatedOn=").append(updatedOn).append("}");
        return sb.toString();
    }
}
