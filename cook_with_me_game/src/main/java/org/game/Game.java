package org.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    final Map<String, Player> players = new HashMap<>();
    private int budgetPerPlayer;
    private int prizePerRound;
    private int numberOfRounds = 5;
    private final List<String> nicks;

    private final Map<String, Integer> products = ProductController.intializeProducts();

    public Game(List<String> nicks, int prize, int numberOfRounds) {
        this.nicks = nicks;
        this.prizePerRound = prize;
        this.numberOfRounds = numberOfRounds;
        this.budgetPerPlayer = GameUtils.calculateBudgetPerPlayer(numberOfRounds);
        for (var nick : nicks) {
            players.put(nick, new Player(budgetPerPlayer, 0, 0));
        }
    }

    public Statistics playTheGame() {
        final Statistics statistics = new Statistics(nicks, players);
        for (int round = 0; round < numberOfRounds; round++) {
            String winner = playTheRound();
            givePrice(players.get(winner));
            notifyLosers(winner);
        }
        return statistics;
    }

    public String playTheRound() {
        return new String(); // winner
    }

    public void givePrice(Player player) {

    }

    public void notifyLosers(String winner) {

    }

}
