package sk.stuba.fei.uim.oop.utility;

public class Bang extends BrownCard {

    public Bang(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
    }

    public void action(Player player, Player targetPlayer) {
    }

    public void action(Player player, Card card) {
    }

    public void action(Player targetPlayer) {

        if (targetPlayer.getBuffs().size() != 0) {
            for (int i = 0; i < targetPlayer.getBuffs().size(); i++) {
                if (targetPlayer.getBuff(i) instanceof Barrel) {
                    System.out.println("Player has a barrel.");
                    Barrel barrel = (Barrel) targetPlayer.getBuff(i);
                    if (barrel.checkDodge()) {
                        return;
                    }
                }
            }
        } else {
            System.out.println("Does " + targetPlayer.getName() + " want to dodge the bullet?\n[Y] / [N]");
            char approval = KeyboardInput.readChar();
            int hasDodge = -1;

            if (approval == 'Y' || approval == 'y') {
                for (int i = 0; i < targetPlayer.getHand().size(); i++) {
                    if (targetPlayer.getCard(i) instanceof Dodge) {
                        hasDodge = i;
                        break;
                    }
                }
                if (hasDodge != -1) {
                    System.out.println(targetPlayer.getName() + " has dodged the bullet.\n\n");
                    getGame().discardCard(targetPlayer, targetPlayer.getCard(hasDodge));
                } else {
                    targetPlayer.setHealth(targetPlayer.getHealth() - 1);
                    System.out.println(targetPlayer.getName() + " took 1 damage.\n\n");
                }
            } else {
                targetPlayer.setHealth(targetPlayer.getHealth() - 5);
                System.out.println(targetPlayer.getName() + " took 1 damage.\n\n");

            }
        }

    }

    public void action() {

    }

    public String getName() {
        return "Bang";
    }
}
