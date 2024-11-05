package org.game;

import lombok.Getter;

import java.util.List;

@Getter
public class Meal {
    private final String name;
    private final List<String> products;
    private final List<String> instructions;
    private final String mealInformation;

    public Meal(String name, List<String> products, List<String> instructions) {
        this.name = name;
        this.products = products;
        this.instructions = instructions;
        this.mealInformation = initializeMealInformation();
    }

    public String initializeMealInformation() {
        StringBuilder sb = new StringBuilder(name + "\n");
        sb.append("\tProducts: ");
        for (final var p : products) {
            sb.append(p);
            sb.append(", ");
        }
        sb.append("\n\tInstructions:");
        for (final var i : instructions){
            sb.append("\n\t\t");
            sb.append(i);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return mealInformation;
    }
}
