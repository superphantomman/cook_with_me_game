package org.game;

import java.util.*;

//TODO Complete Statistics
public class Statistics {
    private final String[] historyRound;
    private final Map<String, List<Meal>> mealsOfPlayers = new HashMap<>();
    private final List<Meal>[] mealsOfRounds;
    private final int rounds;
    private final List<String> nicks;
    private final Map<String, Player> players;

    public Statistics(List<String> nicks, Map<String, Player> players, int rounds) {
        this.nicks = nicks;
        this.players = players;
        for (final var nick : nicks) {
            mealsOfPlayers.put(nick, new ArrayList<>());
        }
        this.mealsOfRounds = new List[rounds];
        this.rounds = rounds;
        historyRound = new String[rounds];
    }

    public void noteRound(int round, List<String> winners, List<Meal> mealsOfRound, Map<String, Meal> mealOfPlayer) {
        this.mealsOfRounds[round] = mealsOfRound;
        this.historyRound[round] = String.format("Round %d\nWinner:%s", round, winners.toString());
        for (final var nick : nicks) {
            final List<Meal> playerMeals = this.mealsOfPlayers.get(nick);
            final Meal mealRoundPlayer = mealOfPlayer.get(nick);
            playerMeals.add(mealRoundPlayer);
        }


    }

    public String determineWinners(Map<String, Player> players) {
        final Deque<String> winners = new ArrayDeque<>(nicks.size());
        int maxVal = 0;
        for (final var nick : nicks) {
            final Player p = players.get(nick);
            if (maxVal < p.getScore()) {
                maxVal = p.getScore();
                winners.clear();
                winners.add(nick);
            } else if (maxVal == p.getScore()) {
                winners.add(nick);
            }
        }
        return "Winners: " + winners;
    }

    public void display() {
        System.out.println("Game");
        System.out.println("Number of round: " + this.rounds);
        System.out.println(determineWinners(players));

        for (int round = 0; round < rounds; round++) {
            System.out.println("Meals of round " + round + "\n" + mealsOfRounds[round]);
        }

        System.out.println("Meals of each player:\n");
        for (final var nick : nicks){
            System.out.println("Meals of player " + nick + "\n" + mealsOfPlayers.get(nick).stream().map(Meal::getName).toList());
        }
    }
}
