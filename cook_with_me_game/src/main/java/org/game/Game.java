package org.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    final Map<String, Player> players = new HashMap<>();
    private final int prizePerRound;
    private int numberOfRounds = 5;
    private final List<String> nicks;

    private final Map<String, Integer> products = ProductController.intializeProducts();

    //Checked
    public Game(List<String> nicks, int prize, int numberOfRounds) {
        this.nicks = nicks;
        this.prizePerRound = prize;
        if (numberOfRounds > 0)
            this.numberOfRounds = numberOfRounds;
        int budgetPerPlayer = GameUtils.calculateBudgetPerPlayer(numberOfRounds);
        for (var nick : nicks) {
            players.put(nick, new Player(nick, budgetPerPlayer, 0));
        }
    }

    public Statistics playTheGame() {
        final Statistics statistics = new Statistics(nicks, players, numberOfRounds);
        for (int round = 0; round < numberOfRounds; round++) {
            //ToCheck
            List<String> winners = playTheRound(round, statistics);
            givePrice(winners);
        }
        return statistics;
    }

    public List<String> playTheRound(int round, Statistics statistics) {
        final List<Meal> mealsOfRound = new ArrayList<>(nicks.size());

        //Players compose their meals
        final Map<String, Meal> mealsPlayer = new HashMap<>();
        for (final var nick : nicks){
            final Player p = players.get(nick);
            final Meal meal = p.composeMeal(products);
            mealsPlayer.put(nick, meal);
            mealsOfRound.add(meal);
        }

        // Voting for a meal
        final Map<String, Integer> votes = new HashMap<>();
        nicks.forEach( n -> votes.put(n, 0)); // initialize votes placeholders

        for (final var n : nicks){
            final Player p = players.get(n);
            String voteForPlayer = p.voteForMeal(nicks, mealsPlayer);
            votes.put(voteForPlayer, votes.get(n) + 1);
        }
        List<String> winners = GameUtils.findWinners(nicks, votes);

        statistics.noteRound(round, winners, mealsOfRound, mealsPlayer);
        return winners; // winner
    }

    public void givePrice(List<String> winners) {
        for (final var winner: winners){
            Player player = players.get(winner);
            player.increaseBudget(prizePerRound);
            player.increaseScore(prizePerRound);
        }

    }


}
