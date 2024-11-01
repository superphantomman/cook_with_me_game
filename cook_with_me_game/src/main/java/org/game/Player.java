package org.game;

import lombok.Getter;

import java.util.Map;

@Getter
public class Player {
    private int score;
    private int wins;
    private int budget;
    private Meal lastCreatedMeal;

    public Player(int budget, int wins, int score) {
        this.budget = budget;
        this.wins = wins;
        this.score = score;
    }
    public Meal composeMeal(Map<String, Integer> products){
        Meal meal = null;
        lastCreatedMeal = meal;
        return meal;
    }


}
