package com.coupondunia.bo.interfaces.impl;

import com.coupondunia.bo.interfaces.BowlingBehaviour;

/**
 * Created by roushan on 14/3/15.
 */
public class BowlingBehaviourImpl implements BowlingBehaviour {
    Boolean canBowl;
    Integer bowlingRank;
    String bowlingStats;

    public BowlingBehaviourImpl(Boolean canBowl, Integer bowlingRank, String bowlingStats){
        this.canBowl = canBowl;
        this.bowlingRank = bowlingRank;
        this.bowlingStats = bowlingStats;
    }

    @Override
    public Boolean isBowler() {
        return canBowl;
    }

    @Override
    public void bowl() {
        //add bowling strategy here
    }

    @Override
    public Integer getBownlingRank() {
        return bowlingRank;
    }

    @Override
    public String getBowlingStats() {
        return bowlingStats;
    }
}
