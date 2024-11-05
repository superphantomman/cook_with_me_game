package org.game;

import java.util.*;

public class GameUtils {
    public static int calculateBudgetPerPlayer(int numberOfRounds ){
        return numberOfRounds * ProductController.getMaxCost() * 3;
    }

    public static List<String> findWinners( List<String> nicks,Map<String, Integer> votes){
        final Deque<String> winners = new ArrayDeque<>(nicks.size());
        int maxVal = 0;
        for (final var n : nicks){
            final int voteForPlayer = votes.get(n);
            if (voteForPlayer > maxVal){
                maxVal = voteForPlayer;
                winners.clear();
                winners.add(n);
            } else if (voteForPlayer == maxVal) {
                winners.add(n);
            }
        }
        return winners.stream().toList();
    }
}
