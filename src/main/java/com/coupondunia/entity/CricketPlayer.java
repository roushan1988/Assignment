package com.coupondunia.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by roushan on 14/3/15.
 */
@Table(name = "cricket_player")
@Entity
public class CricketPlayer extends Player{

    @Column(nullable = false)
    Boolean batsman;

    @Column(nullable = false)
    Boolean bowler;

    @Column(nullable = false)
    Boolean wicketKeeper;

    @Column(nullable = false)
    Boolean allRounder; // Need to create this variable instead of simply deriving it from batsman and bowler because even a wicketkeeping batsman is an allrounder in addition to a batsman cum bowler.
    // this is also in accordance with espncricinfo fantasy league game, where if one selects an allrounder, in most cases, it asks whether to select that player as
    // a batsman or an allrounder. This means that they are not combining the batting and bowling stats to come say that particular player is allrounder. Otherwise,
    // they would have included the player as bolwer as well in those cases.

    @Column(unique = true)
    Integer overallRank; // for allround rank, other info like stats for all rounder will be calculated based on batting, bowling and fielding info
    // I could have used some expression to arrive at overall rank by combining batting bowling and fielding ranks, but, due to subjectivity, I preferred this way.

    @JoinColumn(name = "batting_info_id", unique = true, nullable = false)
    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    BattingInfo battingInfo;

    @JoinColumn(name = "bowling_info_id", unique = true, nullable = false)
    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    BowlingInfo bowlingInfo;

    @JoinColumn(name = "fielding_info_id", unique = true, nullable = false)
    @OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    FieldingInfo fieldingInfo;

    CricketPlayer(){
        super();
    }

    public Boolean getBatsman() {
        return batsman;
    }

    public void setBatsman(Boolean batsman) {
        this.batsman = batsman;
    }

    public Boolean getBowler() {
        return bowler;
    }

    public void setBowler(Boolean bowler) {
        this.bowler = bowler;
    }

    public Boolean getWicketKeeper() {
        return wicketKeeper;
    }

    public void setWicketKeeper(Boolean wicketKeeper) {
        this.wicketKeeper = wicketKeeper;
    }

    public Boolean getAllRounder() {
        return allRounder;
    }

    public Integer getOverallRank() {
        return overallRank;
    }

    public void setOverallRank(Integer overallRank) {
        this.overallRank = overallRank;
    }

    public void setAllRounder(Boolean allRounder) {
        this.allRounder = allRounder;
    }

    public BattingInfo getBattingInfo() {
        return battingInfo;
    }

    public void setBattingInfo(BattingInfo battingInfo) {
        this.battingInfo = battingInfo;
    }

    public BowlingInfo getBowlingInfo() {
        return bowlingInfo;
    }

    public void setBowlingInfo(BowlingInfo bowlingInfo) {
        this.bowlingInfo = bowlingInfo;
    }

    public FieldingInfo getFieldingInfo() {
        return fieldingInfo;
    }

    public void setFieldingInfo(FieldingInfo fieldingInfo) {
        this.fieldingInfo = fieldingInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CricketPlayer{batsman=").append(batsman).append(", bowler=").append(bowler)
                .append(", wicketKeeper=").append(wicketKeeper).append(", overallRank=").append(overallRank)
                .append(", battingInfo=").append(battingInfo).append(", bowlingInfo=").append(bowlingInfo)
                .append(", fieldingInfo=").append(fieldingInfo).append("}");
        return sb.toString();
    }
}
