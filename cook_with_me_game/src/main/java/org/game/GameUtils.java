package org.game;

public class GameUtils {
    public static int calculateBudgetPerPlayer(int numberOfRounds ){
        return numberOfRounds * ProductController.getMaxCost() * 3;
    }
}
