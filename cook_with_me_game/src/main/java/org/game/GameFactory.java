package org.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameFactory {
    private GameFactory(){

    }
    public Game initializeGame(){
        final Scanner s = new Scanner(System.in);

        System.out.println("Type none when you stop adding players");
        List<String> nicks = new ArrayList<>(5);
        String nick;
        do {
            System.out.println("Enter player:");
            nick = s.next();
            nicks.add(nick);
        }while (!nick.equals("none"));

        System.out.println("Enter number of rounds: ");
        final int numberOfRounds = s.nextInt();
        System.out.println("Enter prize per round");
        final int prize = s.nextInt();

        System.out.println("Number of players: " + nicks.size());
        System.out.println("Players: " + nicks);

        return new Game(nicks, prize, numberOfRounds);
    }

    public static GameFactory initializeGameFactory(){
        return new GameFactory();
    }
}
