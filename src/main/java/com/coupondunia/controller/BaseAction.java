package com.coupondunia.controller;

import com.coupondunia.bo.lookups.CricketPlayerLookup;
import com.coupondunia.bo.lookups.TeamCompositionLookup;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;
import java.util.Set;

/**
 * Created by roushan on 15/3/15.
 */
public class BaseAction extends ActionSupport implements Preparable {

    @Autowired
    @Qualifier("teamCompositionLookup")
    public TeamCompositionLookup teamCompositionLookup;

    @Autowired
    @Qualifier("cricketPlayerLookup")
    public CricketPlayerLookup cricketPlayerLookup;

    public Map<Long, Long> getPlayerPriceMap(){
        return cricketPlayerLookup.getPlayerPriceMap();
    }

    public Set<Long> getPlayerPriceMapKeySet(){
        return cricketPlayerLookup.getPlayerPriceMap().keySet();
    }

    @Override
    public void prepare() throws Exception {

    }
}
