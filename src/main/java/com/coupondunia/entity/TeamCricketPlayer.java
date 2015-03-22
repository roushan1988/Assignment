package com.coupondunia.entity;

import com.coupondunia.bo.enums.Type;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by roushan on 22/3/15.
 */
@Table(name = "team_cricket_player")
@Entity
public class TeamCricketPlayer implements Serializable{

    @Id
    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Id
    @JoinColumn(name = "cricket_player_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    CricketPlayer cricketPlayer;

    @Enumerated(EnumType.STRING)
    @Column(name = "team_role", nullable = false)
    Type teamRole;

    TeamCricketPlayer(){

    }

    public TeamCricketPlayer(CricketPlayer cricketPlayer, Long teamId, Type teamRole) {
        this.cricketPlayer = cricketPlayer;
        this.teamId = teamId;
        this.teamRole = teamRole;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public CricketPlayer getCricketPlayer() {
        return cricketPlayer;
    }

    public void setCricketPlayer(CricketPlayer cricketPlayer) {
        this.cricketPlayer = cricketPlayer;
    }

    public Type getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(Type teamRole) {
        this.teamRole = teamRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamCricketPlayer)) return false;

        TeamCricketPlayer that = (TeamCricketPlayer) o;

        if (!cricketPlayer.equals(that.cricketPlayer)) return false;
        if (!teamId.equals(that.teamId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId.hashCode();
        result = 31 * result + cricketPlayer.hashCode();
        return result;
    }
}
