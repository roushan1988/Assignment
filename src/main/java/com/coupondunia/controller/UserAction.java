package com.coupondunia.controller;

import com.coupondunia.bo.enums.Type;
import com.coupondunia.constants.Constants;
import com.coupondunia.entity.*;
import com.coupondunia.service.GenericManager;
import com.coupondunia.service.UserManager;
import com.opensymphony.xwork2.Preparable;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.reflect.Field;

import java.util.*;

/**
 * Created by roushan on 15/3/15.
 */
public class UserAction extends BaseAction implements Preparable, SessionAware {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(UserAction.class);
    private static Map<String, User> userLookup = new HashMap<String, User>();
    private User user;
    private String errorMessage;
    private Long playerId;
    private String types;
    private String teamName;
    private Long teamId;

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

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Autowired
    @Qualifier("userManager")
    private UserManager userManager;

    @Autowired
    @Qualifier("teamManager")
    private GenericManager teamManager;

    @Autowired
    @Qualifier("teamCricketPlayerManager")
    private GenericManager teamCricketPlayerManager;

    private SessionMap<String,Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    private Long balanceAmount;

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Long balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    private Long player1_id;
    private Long player2_id;
    private Long player3_id;
    private Long player4_id;
    private Long player5_id;
    private Long player6_id;
    private Long player7_id;
    private Long player8_id;

    private Long captain_id;

    private String player1_type;
    private String player2_type;
    private String player3_type;
    private String player4_type;
    private String player5_type;
    private String player6_type;
    private String player7_type;
    private String player8_type;

    public Long getPlayer1_id() {
        return player1_id;
    }

    public void setPlayer1_id(Long player1_id) {
        this.player1_id = player1_id;
    }

    public Long getPlayer2_id() {
        return player2_id;
    }

    public void setPlayer2_id(Long player2_id) {
        this.player2_id = player2_id;
    }

    public Long getPlayer3_id() {
        return player3_id;
    }

    public void setPlayer3_id(Long player3_id) {
        this.player3_id = player3_id;
    }

    public Long getPlayer4_id() {
        return player4_id;
    }

    public void setPlayer4_id(Long player4_id) {
        this.player4_id = player4_id;
    }

    public Long getPlayer5_id() {
        return player5_id;
    }

    public void setPlayer5_id(Long player5_id) {
        this.player5_id = player5_id;
    }

    public Long getPlayer6_id() {
        return player6_id;
    }

    public void setPlayer6_id(Long player6_id) {
        this.player6_id = player6_id;
    }

    public Long getPlayer7_id() {
        return player7_id;
    }

    public void setPlayer7_id(Long player7_id) {
        this.player7_id = player7_id;
    }

    public Long getPlayer8_id() {
        return player8_id;
    }

    public void setPlayer8_id(Long player8_id) {
        this.player8_id = player8_id;
    }

    public Long getCaptain_id() {
        return captain_id;
    }

    public void setCaptain_id(Long captain_id) {
        this.captain_id = captain_id;
    }


    public String getPlayer8_type() {
        return player8_type;
    }

    public void setPlayer8_type(String player8_type) {
        this.player8_type = player8_type;
    }

    public String getPlayer2_type() {
        return player2_type;
    }

    public void setPlayer2_type(String player2_type) {
        this.player2_type = player2_type;
    }

    public String getPlayer3_type() {
        return player3_type;
    }

    public void setPlayer3_type(String player3_type) {
        this.player3_type = player3_type;
    }

    public String getPlayer4_type() {
        return player4_type;
    }

    public void setPlayer4_type(String player4_type) {
        this.player4_type = player4_type;
    }

    public String getPlayer5_type() {
        return player5_type;
    }

    public void setPlayer5_type(String player5_type) {
        this.player5_type = player5_type;
    }

    public String getPlayer6_type() {
        return player6_type;
    }

    public void setPlayer6_type(String player6_type) {
        this.player6_type = player6_type;
    }

    public String getPlayer7_type() {
        return player7_type;
    }

    public void setPlayer7_type(String player7_type) {
        this.player7_type = player7_type;
    }

    public String getPlayer1_type() {
        return player1_type;
    }

    public void setPlayer1_type(String player1_type) {
        this.player1_type = player1_type;
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
            Team team = (Team) teamManager.get(user.getId());
            setCaptain_id(team.getCaptain().getId());
            setPlayer1_id(team.getSquad().get(0).getCricketPlayer().getId());
            setPlayer2_id(team.getSquad().get(1).getCricketPlayer().getId());
            setPlayer3_id(team.getSquad().get(2).getCricketPlayer().getId());
            setPlayer4_id(team.getSquad().get(3).getCricketPlayer().getId());
            setPlayer5_id(team.getSquad().get(4).getCricketPlayer().getId());
            setPlayer6_id(team.getSquad().get(5).getCricketPlayer().getId());
            setPlayer7_id(team.getSquad().get(6).getCricketPlayer().getId());
            setPlayer8_id(team.getSquad().get(7).getCricketPlayer().getId());
            setCaptain_id(team.getCaptain().getId());
            setPlayer1_type(team.getSquad().get(0).getTeamRole().name());
            setPlayer2_type(team.getSquad().get(1).getTeamRole().name());
            setPlayer3_type(team.getSquad().get(2).getTeamRole().name());
            setPlayer4_type(team.getSquad().get(3).getTeamRole().name());
            setPlayer5_type(team.getSquad().get(4).getTeamRole().name());
            setPlayer6_type(team.getSquad().get(5).getTeamRole().name());
            setPlayer7_type(team.getSquad().get(6).getTeamRole().name());
            setPlayer8_type(team.getSquad().get(7).getTeamRole().name());
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
            userManager.flush();
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
        if(user==null){
            return INPUT;
        }
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

    public String fetchTypes(){
        List<String> list = new ArrayList<String>();
        if(playerId == null){
            types="";
            return SUCCESS;
        }
        CricketPlayer player = cricketPlayerLookup.getById(playerId);
        if(player.getBatsman()){
            list.add("BATSMAN");
        }
        if(player.getBowler()){
            list.add("BOWLER");
        }
        if(player.getWicketKeeper()){
            list.add("WICKETKEEPER");
        }
        if(player.getAllRounder()){
            list.add("ALLROUNDER");
        }
        types = joinList(list, ",");
        return SUCCESS;
    }

    public String addTeam(){
        try{
            logger.info("addTeam method called");
            Team team = new Team();
            StringBuffer playerVariable = new StringBuffer("Player");
            List<TeamCricketPlayer> playerList = new ArrayList<TeamCricketPlayer>();
            Date date = new Date();
            if(teamId != null){
                team.setId(teamId);
            }else{
                team.setCreatedOn(date);
            }
            team.setUpdatedOn(date);
            team.setName(teamName);
            team.setCaptain(cricketPlayerLookup.getById(captain_id));
            if(teamId != null){
                team.setId(teamId);
                team = (Team) teamManager.save(team);
            }else{
                team.setCreatedOn(date);
                team = (Team) teamManager.saveWithUpdate(team);
            }
            teamManager.flush();
            teamId = team.getId();

            TeamCricketPlayer player1 = new TeamCricketPlayer(cricketPlayerLookup.getById(player1_id), teamId, Type.valueOf(player1_type));
            TeamCricketPlayer player2 = new TeamCricketPlayer(cricketPlayerLookup.getById(player2_id), teamId, Type.valueOf(player2_type));
            TeamCricketPlayer player3 = new TeamCricketPlayer(cricketPlayerLookup.getById(player3_id), teamId, Type.valueOf(player3_type));
            TeamCricketPlayer player4 = new TeamCricketPlayer(cricketPlayerLookup.getById(player4_id), teamId, Type.valueOf(player4_type));
            TeamCricketPlayer player5 = new TeamCricketPlayer(cricketPlayerLookup.getById(player5_id), teamId, Type.valueOf(player5_type));
            TeamCricketPlayer player6 = new TeamCricketPlayer(cricketPlayerLookup.getById(player6_id), teamId, Type.valueOf(player6_type));
            TeamCricketPlayer player7 = new TeamCricketPlayer(cricketPlayerLookup.getById(player7_id), teamId, Type.valueOf(player7_type));
            TeamCricketPlayer player8 = new TeamCricketPlayer(cricketPlayerLookup.getById(player8_id), teamId, Type.valueOf(player8_type));
            teamCricketPlayerManager.save(player1);
            teamCricketPlayerManager.save(player2);
            teamCricketPlayerManager.save(player3);
            teamCricketPlayerManager.save(player4);
            teamCricketPlayerManager.save(player5);
            teamCricketPlayerManager.save(player6);
            teamCricketPlayerManager.save(player7);
            teamCricketPlayerManager.save(player8);
            teamCricketPlayerManager.flush();

            String loggedUsername = (String) sessionMap.get("username");
            user = userLookup.get(loggedUsername);
            user.setBalanceAmount(balanceAmount);
            user.setTeam(team);
            userManager.save(user);
            userLookup.remove(user.getUsername());
            userLookup.put(user.getUsername(), user);
            return SUCCESS;
        }catch (Exception e){
            logger.error("Exception occured", e);
            errorMessage = "Some error occured";
            return ERROR;
        }
    }

    private static String joinList(List list, String literal){
        return list.toString().replaceAll(",", literal).replaceAll("[\\[.\\].\\s+]", "");
    }
}
