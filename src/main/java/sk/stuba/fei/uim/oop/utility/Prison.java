package sk.stuba.fei.uim.oop.utility;

public class Prison extends BlueCard {

    public Prison(Game name) {
        super(name);
    }

    public void action(Player player, Player targetPlayer, Card card) {
        player.removeCard(card);
        targetPlayer.addBuff(card);
        targetPlayer.putInPrison();
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
        return "Prison";
    }

    public void uvazni(Player player) {
        player.putInPrison();
    }

    public void checkEscape(Player player) {
        if (rand.nextInt(71) < (int) (71 / 4)) {
            player.releaseFromPrison();
        }
    }
}