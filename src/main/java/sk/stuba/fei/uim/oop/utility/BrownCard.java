package sk.stuba.fei.uim.oop.utility;

public abstract class BrownCard extends Card {

    public BrownCard(Game game) {
        super(game);
    }

    public abstract void action(Player player, Player targetPlayer, Card card);

    public abstract void action(Player player, Player targetPlayer);

    public abstract void action(Player player, Card card);

    public abstract void action(Player player);

    public abstract void action();

    public abstract String getName();
}