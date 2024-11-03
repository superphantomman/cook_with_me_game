package org.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Getter
public class Player {
    private final String nick;
    private int score;
    private int wins;
    private int budget;
    private Meal lastCreatedMeal;

    public Player(String nick, int budget, int wins, int score) {
        this.nick = nick;
        this.budget = budget;
        this.wins = wins;
        this.score = score;
    }
    public Meal composeMeal(Map<String, Integer> products){
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Make your choice  " + this.nick + ".");



        //List all products and theirs cost
        String product = "none";
        List<String > chosenProducts = new ArrayList<>();
        System.out.println("Type none when you stop adding products");
        do {
            System.out.println("Enter your product: ");
            product = scanner.next();
            if (!product.equals("none"))
                chosenProducts.add(product);
        }while ( !product.equals("none"));

        List<String> instructions = new ArrayList<>();
        String instruction = "none";
        System.out.println("Type none when you stop adding instructions");
        do {
            System.out.println("Enter your product: ");
            instruction = scanner.next();
            if (!instruction.equals("none"))
                instructions.add(product);
        }while ( !instruction.equals("none"));

        System.out.println("Enter your meal name: ");
        final String mealName = scanner.next();

        Meal meal = new Meal(mealName, chosenProducts, instructions);
        lastCreatedMeal = meal;
        return meal;
    }
    public String voteForMeal(List<String> allNicks, Map<String, Meal> meals) {

        final Scanner scanner = new Scanner(System.in);
        System.out.println("Make your choice  " + this.nick + ".");

        //Display player's meals
        for (var n : allNicks) {
            final Meal meal = meals.get(n);
            if ( n.equals(this.nick))
                continue;
            System.out.println("Player: " + n);
            System.out.println("Meal:");
            System.out.println(meal);
        }

        System.out.println("\n\n\n\n");
        scanner.next();
        for (int i = 0; i < allNicks.size(); i++) {
            final String n = allNicks.get(i);
            final Meal m = meals.get(n);
            if ( n.equals(this.nick))
                continue;
            System.out.printf("Option: %d\nPlayer:%s\nMeal: %s\n\n\n", i, n, m);
        }

        System.out.println("Enter option number: ");
        final int option = scanner.nextInt();
        return allNicks.get(option);
    }


    public void increaseScore(int prizeScore){
        this.score +=prizeScore;
    }
    public void increaseBudget(int prizeBudget){
        this.budget += prizeBudget;
    }
}
