package sk.stuba.fei.uim.oop.utility;

public class Dynamite extends BlueCard {

    public Dynamite(Game game) {
        super(game);
    }

    public void action(Player player, Player targetPlayer, Card card) {
        player.removeCard(card);
        targetPlayer.addBuff(card);
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
        return "Dynamite";
    }

    public void explodeCheck(Player player, Card dynamite) {
        getGame().dealCards(player, 1);
        getGame().discardCard(player, player.getCard(player.getHand().size()));
        if (rand.nextInt(71) < (int) (71 / 4)) {
            player.setHealth(player.getHealth() - 3);
            player.removeBuff(dynamite);
        } else {
            if (player.getId() == 1) {
                getGame().getPlayers().get(getGame().getPlayers().size() - 1).addBuff(dynamite);
            } else {
                getGame().getPlayers().get(player.getId() - 2).addBuff(dynamite);
            }
            player.removeBuff(dynamite);
        }
    }

}
