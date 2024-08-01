package Servs;

import Model.User;
import Model.Team;

import java.util.List;

public interface TeamServ {

    //Trivial Services
    public Team addTeam(Team t);
    public Team getTeam(int id);
    public List<Team> getAllTeams();
    public Team updateTeam(Team change);
    public boolean deleteTeam(int id);

    //Can be other more interesting services
    public Team getTeam(String name);
    public Team winAgainst(int winner_id, int loser_id);

}