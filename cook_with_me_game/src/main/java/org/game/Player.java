package org.game;

import lombok.Getter;

import java.util.*;

@Getter
public class Player {
    private final String nick;
    private int score;

    private int budget;
    private Meal lastCreatedMeal;

    public Player(String nick, int budget, int score) {
        this.nick = nick;
        this.budget = budget;
        this.score = score;
    }

    public Meal composeMeal(Map<String, Integer> products) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Make your choice  " + this.nick + ".");


        //List all products and theirs cost
        String product;
        List<String> chosenProducts = new ArrayList<>();
        final Set<String> productSet = products.keySet();
        System.out.println("Type none when you stop adding products");
        do {
            System.out.println("Enter your product: ");
            product = scanner.nextLine();

            if (product.equals("none"))
                continue;

            if (!productSet.contains(product)) {
                System.out.println("Skipped because product is not in base. Try again passing new product");
                continue;
            }
            int prize = products.get(product);
            if (budget - prize < 0) {
                System.out.println("Your budget is too low for this product. Try again passing new product.");
                continue;
            }
            this.budget -= prize;

            chosenProducts.add(product);
        } while (!product.equals("none"));

        List<String> instructions = new ArrayList<>();
        String instruction;
        System.out.println("Type none when you stop adding instructions");
        do {
            System.out.println("Enter your instruction: ");
            instruction = scanner.nextLine();
            if (instruction.equals("none"))
                continue;
            instructions.add(instruction);
        } while (!instruction.equals("none"));

        System.out.println("Enter your meal name: ");
        final String mealName = scanner.nextLine();

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
            if (n.equals(this.nick))
                continue;
            System.out.println("Player: " + n);
            System.out.println("Meal: " + meal.getName());
            System.out.println(meal);
        }

        System.out.println("\n\n");
        System.out.println("Read menu and type enter when you finish.");
        scanner.nextLine();
        for (int i = 0; i < allNicks.size(); i++) {
            final String n = allNicks.get(i);
            final Meal m = meals.get(n);
            if (n.equals(this.nick))
                continue;
            System.out.printf("Option: %d\nPlayer:%s\nMeal: %s\n\n\n", i, n, m.getName());
        }

        System.out.println("Enter option number: ");
        final int option = scanner.nextInt();
        return allNicks.get(option);
    }


    public void increaseScore(int prizeScore) {
        this.score += prizeScore;
    }

    public void increaseBudget(int prizeBudget) {
        this.budget += prizeBudget;
    }
}
