package com.coupondunia.bo.interfaces.impl;

import com.coupondunia.bo.interfaces.BattingBehaviour;

/**
 * Created by roushan on 14/3/15.
 */
public class BattingBehaviourImpl implements BattingBehaviour {
    Boolean canBat;
    Integer battingRank;
    String battingStats;

    public BattingBehaviourImpl(Boolean canBat, Integer battingRank, String battingStats){
        this.canBat = canBat;
        this.battingRank = battingRank;
        this.battingStats = battingStats;
    }
    @Override
    public Boolean isBatsman() {
        return canBat;
    }

    @Override
    public void bat() {
        // add batting strategy here
    }

    @Override
    public Integer getBattingRank() {
        return battingRank;
    }

    @Override
    public String getBattingStats() {
        return battingStats;
    }
}
