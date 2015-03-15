package com.coupondunia.dao;

import com.coupondunia.entity.Player;

import java.util.List;

/**
 * Created by roushan on 15/3/15.
 */
public interface PlayerDao {
    public void addCricketPlayer(Player player);
    public List<Player> getAllPlayers();
    public void deletePlayer(Integer id);
}
