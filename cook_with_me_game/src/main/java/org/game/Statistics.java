package org.game;

import java.util.List;
import java.util.Map;

//TODO Complete Statistics
public class Statistics{
    List<String> nicks;
    Map<String, Player> players;

    public Statistics(List<String> nicks, Map< String ,Player> players) {
        this.nicks = nicks;
        this.players = players;
    }
    public void noteRound(int round, String winner, Meal meal){

    }

    public String determineWinner(){
        throw new UnsupportedOperationException();
    }
}
