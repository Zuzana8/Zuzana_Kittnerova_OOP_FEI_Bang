package sk.stuba.fei.uim.oop.utility;

import java.util.List;

public class Indians extends BrownCard {

    public Indians(Game game) {
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

    public void action() {
    }

    public String getName() {
        return "Indians";
    }

    public void action(Player player) {
        List<Player> players = getGame().otherPlayers(player);

        for (Player attackedPlayer : players) {

            System.out.println("Player " + player.getName() + " used Indians!\n" + "will  "
                    + attackedPlayer.getName() + " use Bang?\n[Y] / [N]");
            char approval = KeyboardInput.readChar();
            int hasBang = -1;

            if (approval == 'Y' || approval == 'y') {
                for (int i = 0; i < attackedPlayer.getHand().size(); i++) {
                    if (attackedPlayer.getCard(i) instanceof Bang) {
                        hasBang = i;
                        break;
                    }
                }
                if (hasBang != -1) {
                    System.out.println(attackedPlayer.getName() + " killed his indian.\n\n");
                    getGame().discardCard(attackedPlayer, attackedPlayer.getCard(hasBang));
                } else {
                    attackedPlayer.setHealth(attackedPlayer.getHealth() - 1);
                    System.out.println(attackedPlayer.getName() + " took 1 damage.\n\n");
                    if (getGame().checkHealth(attackedPlayer)) {
                        getGame().removePlayer(attackedPlayer);
                    }
                }
            } else {
                attackedPlayer.setHealth(attackedPlayer.getHealth() - 1);
                System.out.println(attackedPlayer.getName() + " took 1 damage.\n\n");
                if (getGame().checkHealth(attackedPlayer)) {
                    getGame().removePlayer(attackedPlayer);
                }
            }

        }
    }
}
