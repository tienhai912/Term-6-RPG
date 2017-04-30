package dev.game;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("MONSTER LIFE", 900, 640);
        game.start();
    }
}