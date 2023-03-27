package sk.stuba.fei.uim.oop.utility;

public class Barrel extends BlueCard {

    public Barrel(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
    }

    public void action(Player player, Player targetPlayer) {
    }

    public void action(Player player, Card card) {
        player.removeCard(card);
        player.addBuff(card);
    }

    public void action(Player player) {
    }

    public void action() {
    }

    public String getName() {
        return "Barrel";
    }

    public boolean checkDodge() {
        if (rand.nextInt(71) < (int) (71 / 4)) {
            return true;
        }
        return false;
    }
}