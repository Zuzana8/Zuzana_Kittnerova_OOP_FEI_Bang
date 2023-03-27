package sk.stuba.fei.uim.oop.utility;

public class Dodge extends BrownCard {

    public Dodge(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
    }

    public void action(Player player, Player targetPlayer) {
    }

    public void action(Player player, Card card) {
    }

    public void action(Player player) {
        System.out.println(player + " dodged a bullet.");
    }

    public void action() {
        System.out.println("Dodged a bullet.");
    }

    public String getName() {
        return "Dodge";
    }
}
