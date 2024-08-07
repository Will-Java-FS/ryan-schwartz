package com.revature.Services;
import com.revature.Model.Team;
import com.revature.Repos.TeamsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeamServImp implements TeamServ {
    @Autowired
    TeamsRepo tr;

    //Trivial Services
    @Override
    public Team addTeam(Team t)
    {
        return tr.save(t);
    }
    public Team getTeam(int id)
    {
        Optional<Team> temp = tr.findById(id);

        return temp.isPresent() ? temp.get() : null;
    }
    public List<Team> getAllTeams()
    {
        return (List<Team>) tr.findAll();
    }
    public Team updateTeam(Team change, int id)
    {
        if(getTeam(id) == null) {
            return null;
        } else
        {
            change.setId(id);
            return tr.save(change);
        }
    }
    public boolean deleteTeam(int id)
    {
        try {
            tr.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Can be other more interesting services
    public Team getTeam(String name)
    {
        return tr.findByName(name);
    }

    @Transactional
    public Team winAgainst(int winner_id, int loser_id)
    {
        Optional<Team> winner = tr.findById(winner_id);
        Optional<Team> loser = tr.findById(loser_id);
        if(winner.isPresent() && loser.isPresent())
        {
            Team w = winner.get();
            Team l = loser.get();
            w.setWins(w.getWins() + 1);
            l.setLoses(l.getLoses() +1);
             tr.save(l);
            return tr.save(w);
        }
        return null;
    }
}
