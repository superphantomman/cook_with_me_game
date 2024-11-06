package org.game;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(List.of("Jack", "Emily"),200,2);
        Statistics statistics = game.playTheGame();
        statistics.display();
    }

}