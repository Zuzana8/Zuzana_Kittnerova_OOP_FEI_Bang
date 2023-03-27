package sk.stuba.fei.uim.oop.utility;

public class Beer extends BrownCard {

    public Beer(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
    }

    public void action(Player player, Player targetPlayer) {
    }

    public void action(Player player, Card card) {
    }

    public void action(Player player) {
        player.setHealth(player.getHealth() + 1);
    }

    public void action() {
    }

    public String getName() {
        return "Beer";
    }

}