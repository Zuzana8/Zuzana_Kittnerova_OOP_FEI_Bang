package sk.stuba.fei.uim.oop.utility;

public class CatBalou extends BrownCard {

    public CatBalou(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
        if (card instanceof Barrel || card instanceof Prison || card instanceof Dynamite) {
            if (card instanceof Prison)
                targetPlayer.releaseFromPrison();
            targetPlayer.removeBuff(card);
        } else
            targetPlayer.removeCard(card);
    }

    public void action(Player player, Player targetPlayer) {

    }

    public void action(Player player, Card card) {

    }

    public void action(Player player) {
    }

    public void action() {
    }

    public String getName() {
        return "Cat Balou";
    }
}
