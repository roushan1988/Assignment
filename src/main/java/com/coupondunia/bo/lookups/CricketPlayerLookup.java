package com.coupondunia.bo.lookups;

import com.coupondunia.dao.GenericDao;
import com.coupondunia.entity.CricketPlayer;

import java.util.*;

/**
 * Created by roushan on 17/3/15.
 */
public class CricketPlayerLookup extends AbstractItemLookup<CricketPlayer> implements ILookup<String, CricketPlayer>{

    private List<CricketPlayer> batsmanList = new ArrayList<CricketPlayer>();
    private List<CricketPlayer> bowlerList = new ArrayList<CricketPlayer>();
    private List<CricketPlayer> wicketKeeperList = new ArrayList<CricketPlayer>();
    private List<CricketPlayer> allRounderList = new ArrayList<CricketPlayer>();
    private Map<Long, Long> playerPriceMap = new HashMap<Long, Long>();

    public List<CricketPlayer> getBatsmanList() {
        return batsmanList;
    }

    public List<CricketPlayer> getBowlerList() {
        return bowlerList;
    }

    public List<CricketPlayer> getWicketKeeperList() {
        return wicketKeeperList;
    }

    public List<CricketPlayer> getAllRounderList() {
        return allRounderList;
    }

    public Map<Long, Long> getPlayerPriceMap() {
        return playerPriceMap;
    }

    public List<String> getMasterList(){
        return masterList;
    }

    private GenericDao<CricketPlayer, Long> cricketPlayerDao; // Spring injected

    public void setCricketPlayerDao(GenericDao<CricketPlayer, Long> cricketPlayerDao) {
        this.cricketPlayerDao = cricketPlayerDao;
    }

    private static final Comparator<CricketPlayer> COMPARATOR = new Comparator<CricketPlayer>() {
        @Override
        public int compare(CricketPlayer c1, CricketPlayer c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };

    public CricketPlayerLookup(SystemResource systemResource, final String resourceName) {
        super(systemResource, resourceName, COMPARATOR);
    }

    @Override
    protected List<CricketPlayer> getAllItems() {
        return cricketPlayerDao.getAll();
    }

    @Override
    public void onReload(){
        super.onReload();
        updateSpecificLists();
    }

    @Override
    public CricketPlayer add(CricketPlayer player){
        super.add(player);
        updateSpecificLists();
        return player;
    }

    private void updateSpecificLists(){
        List<CricketPlayer> tempBatsmanList = new ArrayList<CricketPlayer>();
        List<CricketPlayer> tempBowlerList = new ArrayList<CricketPlayer>();
        List<CricketPlayer> tempWicketKeeperList = new ArrayList<CricketPlayer>();
        List<CricketPlayer> tempAllRounderList = new ArrayList<CricketPlayer>();
        Map<Long, Long> tempPlayerPriceMap = new HashMap<Long, Long>();
        for(CricketPlayer player: getAll()){
            if(idLookupMap.get(player.getId()).getBatsman()){
                tempBatsmanList.add(player);
            }
            if(idLookupMap.get(player.getId()).getBowler()){
                tempBowlerList.add(player);
            }
            if(idLookupMap.get(player.getId()).getWicketKeeper()){
                tempWicketKeeperList.add(player);
            }
            if(idLookupMap.get(player.getId()).getAllRounder()){
                tempAllRounderList.add(player);
            }
            tempPlayerPriceMap.put(player.getId(), player.getPrice());
        }
        LOCK.writeLock().lock();
        try{
            batsmanList = tempBatsmanList;
            bowlerList = tempBowlerList;
            wicketKeeperList = tempWicketKeeperList;
            allRounderList = tempAllRounderList;
            playerPriceMap = tempPlayerPriceMap;
        } finally{
            LOCK.writeLock().unlock();
        }
    }
}
