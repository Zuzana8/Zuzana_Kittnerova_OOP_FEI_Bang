package sk.stuba.fei.uim.oop.utility;

import java.util.Random;

public abstract class Card {
    private Game game;
    Random rand = new Random();

    public Card(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public abstract void action(Player player, Player targetPlayer, Card card);

    public abstract void action(Player player, Player targetPlayer);

    public abstract void action(Player player, Card card);

    public abstract void action(Player player);

    public abstract void action();

    public abstract String getName();
}
