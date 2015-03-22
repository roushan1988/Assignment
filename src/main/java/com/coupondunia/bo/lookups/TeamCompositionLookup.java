package com.coupondunia.bo.lookups;

import com.coupondunia.dao.GenericDao;
import com.coupondunia.entity.TeamComposition;

import java.util.Comparator;
import java.util.List;

/**
 * Created by roushan on 17/3/15.
 */
public class TeamCompositionLookup extends AbstractItemLookup<TeamComposition> implements ILookup<String, TeamComposition>{

    private GenericDao<TeamComposition, Long> teamCompositionDao; // Spring injected

    public void setTeamCompositionDao(GenericDao<TeamComposition, Long> teamCompositionDao) {
        this.teamCompositionDao = teamCompositionDao;
    }

    private static final Comparator<TeamComposition> COMPARATOR = new Comparator<TeamComposition>() {
        @Override
        public int compare(TeamComposition c1, TeamComposition c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };

    public TeamCompositionLookup(SystemResource systemResource, final String resourceName) {
        super(systemResource, resourceName, COMPARATOR);
    }

    @Override
    protected List<TeamComposition> getAllItems() {
        return teamCompositionDao.getAll();
    }
}
