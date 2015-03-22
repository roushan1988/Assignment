package com.coupondunia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by roushan on 17/3/15.
 */
@Table(name = "team_composition")
@Entity
public class TeamComposition extends LookupItem{

    @Column(name = "batsman_count", nullable = false)
    Integer batsmenCount;

    @Column(name = "bowler_count", nullable = false)
    Integer bowlerCount;

    @Column(name = "wicket_keeper_count", nullable = false)
    Integer wicketKeeperCount;

    @Column(name = "all_rounder_count", nullable = false)
    Integer allRounderCount;

    public Integer getBatsmenCount() {
        return batsmenCount;
    }

    public void setBatsmenCount(Integer batsmenCount) {
        this.batsmenCount = batsmenCount;
    }

    public Integer getBowlerCount() {
        return bowlerCount;
    }

    public void setBowlerCount(Integer bowlerCount) {
        this.bowlerCount = bowlerCount;
    }

    public Integer getWicketKeeperCount() {
        return wicketKeeperCount;
    }

    public void setWicketKeeperCount(Integer wicketKeeperCount) {
        this.wicketKeeperCount = wicketKeeperCount;
    }

    public Integer getAllRounderCount() {
        return allRounderCount;
    }

    public void setAllRounderCount(Integer allRounderCount) {
        this.allRounderCount = allRounderCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TeamComposition{").append("batsmenCount=").append(batsmenCount).append(", bowlerCount=")
                .append(bowlerCount).append(", wicketKeeperCount=").append(wicketKeeperCount).append(", allRounderCount=")
                .append(allRounderCount).append('}');

        return sb.toString();
    }
}
