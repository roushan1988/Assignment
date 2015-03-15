package com.coupondunia.bo.interfaces.impl;

import com.coupondunia.bo.interfaces.FieldingBehaviour;

/**
 * Created by roushan on 14/3/15.
 */
public class FieldingBehaviourImpl implements FieldingBehaviour {
    Boolean canWicketKeep;
    Integer fieldingRank;
    String fieldingStats;

    public FieldingBehaviourImpl(Boolean canWicketKeep, Integer fieldingRank, String fieldingStats){
        this.canWicketKeep = canWicketKeep;
        this.fieldingRank = fieldingRank;
        this.fieldingStats = fieldingStats;
    }

    @Override
    public Boolean isWicketKeeper() {
        return canWicketKeep;
    }

    @Override
    public void field() {
        // add fielding strategy here
    }

    @Override
    public Integer getFieldingRank() {
        return fieldingRank;
    }

    @Override
    public String getFieldingStats() {
        return fieldingStats;
    }
}
