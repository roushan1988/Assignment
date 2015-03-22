package com.coupondunia.controller;

import com.coupondunia.bo.lookups.CricketPlayerLookup;
import com.coupondunia.bo.lookups.TeamCompositionLookup;
import com.coupondunia.entity.CricketPlayer;
import com.coupondunia.entity.User;
import com.coupondunia.service.CricketPlayerManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roushan on 18/3/15.
 */
public class PlayerAction extends BaseAction implements Preparable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(PlayerAction.class);
    private CricketPlayer cricketPlayer;
    private String errorMessage;

    public CricketPlayer getCricketPlayer() {
        return cricketPlayer;
    }

    public void setCricketPlayer(CricketPlayer cricketPlayer) {
        this.cricketPlayer = cricketPlayer;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Autowired
    @Qualifier("cricketPlayerManager")
    private CricketPlayerManager cricketPlayerManager;

    //This method will be called before any of Action method is invoked;
    //So some pre-processing if required.
    @Override
    public void prepare() throws Exception {
    }

    public String execute() {
        return SUCCESS;
    }

    public String addPlayer(){
        try{
            logger.info("addPlayer method called");
            cricketPlayer.setCreatedOn(new Date());
            cricketPlayer.setUpdatedOn(new Date());
            cricketPlayer.getBattingInfo().setCreatedOn(new Date());
            cricketPlayer.getBattingInfo().setUpdatedOn(new Date());
            cricketPlayer.getBowlingInfo().setCreatedOn(new Date());
            cricketPlayer.getBowlingInfo().setUpdatedOn(new Date());
            cricketPlayer.getFieldingInfo().setCreatedOn(new Date());
            cricketPlayer.getFieldingInfo().setUpdatedOn(new Date());
            cricketPlayerManager.saveWithUpdate(cricketPlayer);
            cricketPlayerLookup.add(cricketPlayer);
            return SUCCESS;
        }catch (Exception e){
            logger.error("Exception occurred", e);
            errorMessage = "Some error occurred";
            return ERROR;
        }
    }
}
