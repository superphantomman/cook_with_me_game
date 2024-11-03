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
        //Fill it
        this.mealInformation = "";
    }

    public String getMealInformation(){
        return "";
    }

    @Override
    public String toString() {
        return mealInformation;
    }
}
