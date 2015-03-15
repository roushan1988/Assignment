package com.coupondunia.controller;

import com.coupondunia.constants.Constants;
import com.coupondunia.entity.User;
import com.coupondunia.service.UserManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roushan on 15/3/15.
 */
public class UserAction extends ActionSupport implements Preparable, SessionAware {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserAction.class);
    private static Map<String, User> userLookup = new HashMap<String, User>();
    private User user;
    private String errorMessage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Autowired
    @Qualifier("userManager")
    private UserManager userManager;

    private SessionMap<String,Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    //This method will be called before any of Action method is invoked;
    //So some pre-processing if required.
    @Override
    public void prepare() throws Exception {
    }

    public String execute(){
        String loggedUsername = null;

        // check if the userName is already stored in the session
        if (sessionMap.containsKey("username")) {
            loggedUsername = (String) sessionMap.get("username");
        }
        if (loggedUsername != null) {
            user = userLookup.get(loggedUsername);
            return SUCCESS; // return welcome page
        }

        // in other cases, return login page
        return INPUT;
    }

    public String logout(){
        if(sessionMap!=null){
            sessionMap.invalidate();
        }
        return SUCCESS;
    }

    public String addUser(){
        try{
            logger.info("addUser method called");
            user.setStartingAmount(Constants.STARTING_AMOUNT);
            user.setBalanceAmount(Constants.STARTING_AMOUNT);
            user.setCreatedOn(new Date());
            user.setUpdatedOn(new Date());
            userManager.save(user);
            return SUCCESS;
        }catch (Exception e){
            logger.error("Exception occured", e);
            errorMessage = "Some error occured";
            return ERROR;
        }
    }

    public String signupPage(){
        return SUCCESS;
    }

    public String login(){
        logger.info("login method called");
        user = userManager.login(user.getUsername(), user.getEmail(), user.getPassword());
        if(user != null){
            sessionMap.put("username",user.getUsername());
            userLookup.remove(user.getUsername());
            userLookup.put(user.getUsername(), user);
            return SUCCESS;
        }
        errorMessage = "Some error occured";
        return ERROR;
    }
}
