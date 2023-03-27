package sk.stuba.fei.uim.oop.utility;

public class Stagecoach extends BrownCard {

    public Stagecoach(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
    }

    public void action(Player player, Player targetPlayer) {
    }

    public void action(Player player, Card card) {
    }

    public void action(Player player) {
        getGame().dealCards(player, 2);
    }

    public void action() {
    }

    public String getName() {
        return "Dostavnik";
    }

}
