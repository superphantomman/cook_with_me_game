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
        if (numberOfRounds > 0)
            this.numberOfRounds = numberOfRounds;
        this.budgetPerPlayer = GameUtils.calculateBudgetPerPlayer(numberOfRounds);
        for (var nick : nicks) {
            players.put(nick, new Player(nick,budgetPerPlayer, 0, 0));
        }
    }

    public Statistics playTheGame() {
        final Statistics statistics = new Statistics(nicks, players);
        for (int round = 0; round < numberOfRounds; round++) {
            List<String> winners = playTheRound(round);
            givePrice(winners);
        }
        return statistics;
    }

    public List<String> playTheRound(int round) {

        //Players compose their meals
        final Map<String, Meal> meals = new HashMap<>();
        for (final var nick : nicks){
            final Player p = players.get(nick);
            meals.put(nick, p.composeMeal(products));
        }

        // Voting for a meal
        final Map<String, Integer> votes = new HashMap<>();
        nicks.forEach( n -> votes.put(n, 0)); // initialize votes placeholders

        for (final var n : nicks){
            final Player p = players.get(n);
            String voteForPlayer = p.voteForMeal(nicks, meals);
            votes.put(n, votes.get(n) + 1);
        }

        return GameUtils.findWinners(nicks,votes); // winner
    }

    public void givePrice(List<String> winners) {
        for (final var winner: winners){
            Player player = players.get(winner);
            player.increaseBudget(prizePerRound);
            player.increaseScore(prizePerRound);
        }

    }


}
