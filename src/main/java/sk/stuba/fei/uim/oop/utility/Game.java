package sk.stuba.fei.uim.oop.utility;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;

    public Game(int numPlayers) {
        this.players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            this.players.add(new Player("Player " + i, i, this));
        }
        this.deck = new Deck(this);
        this.discardPile = new ArrayList<>();
        startGame();
    }

    public void dealCards(Player player, int number) {
        for (int i = 0; i < number; i++) {
            Card card = deck.drawCard();
            player.getHand().add(card);
        }
    }

    public void discardCard(Player player, Card card) {
        discardPile.add(card);
        player.getHand().remove(card);
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void CLS() {
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
    }

    public void takeTurn(Player player) {
        int cardToPlay = 0;
        int cardToDiscard;
        int cardToSteal;
        Player targetPlayer;
        boolean playedBang = false;

        if (player.getBuffs().size() != 0) {
            for (int i = 0; i < player.getBuffs().size(); i++) {
                if (player.getBuff(i) instanceof Dynamite) {
                    System.out.println("Player has dynamite.");
                    Dynamite dynamite = (Dynamite) player.getBuff(i);
                    dynamite.explodeCheck(player, dynamite);
                    continue;
                }

                if (player.getBuff(i) instanceof Prison) {
                    System.out.println(player.getName() + " is imprisoned.");
                    Prison prison = (Prison) player.getBuff(i);
                    prison.checkEscape(player);
                    player.removeBuff(prison);
                    continue;
                }

            }
        }

        if (player.inPrison()) {
            player.releaseFromPrison();
            System.out.println("Skipping " + player.getName() + "'s turn'.\n");
            return;
        }

        dealCards(player, 2);

        while (true) {
            if (!(players.size() > 1) || player.getHealth() <= 0)
                return;
            CLS();
            System.out.println("\t" + player.getName() + "'s turn");
            System.out.println("\tHealth : " + player.getHealth());
            System.out.println("\tCards : " + player.getHand().size());
            System.out.println();
            for (Player enemy : otherPlayers(player)) {
                System.out.println("Enemy: " + enemy.getName() + " has " + enemy.getHealth() + " health and is holding "
                        + enemy.getHand().size() + " cards.");
            }

            System.out.println();
            if (player.getBuffs().size() != 0) {
                System.out.print("Your buffs: ");
                for (Card buff : player.getBuffs())
                    System.out.print(buff.getName() + ", ");
                System.out.print("\n");
            }

            System.out.println();

            if (cardToPlay != -1) {
                System.out.println("[0] End turn / Discard cards");
                for (Card card : player.getHand()) {
                    System.out.println("[" + (player.getHand().indexOf(card) + 1) + "] " + card.getName());
                }

                System.out.print("Card to play: ");
                cardToPlay = KeyboardInput.readInt() - 1;

                if (cardToPlay >= player.getHand().size() || cardToPlay < -1)
                    continue;

                if (cardToPlay == -1)
                    continue;

                if (playedBang && player.getCard(cardToPlay) instanceof Bang) {
                    System.out.println("Bang already played. Chose different card.\n");
                    continue;
                }

                if (player.getCard(cardToPlay) instanceof Barrel) {
                    player.getCard(cardToPlay).action(player, player.getCard(cardToPlay));
                    continue;
                }
                if (player.getCard(cardToPlay) instanceof Dodge) {
                    System.out.print("You cannot play dodge on its own. Select other card.");
                    continue;
                }
                if (player.getCard(cardToPlay) instanceof Indians || player.getCard(cardToPlay) instanceof Beer
                        || player.getCard(cardToPlay) instanceof Stagecoach) {
                    player.getCard(cardToPlay).action(player);
                    discardCard(player, player.getCard(cardToPlay));
                    continue;
                }
                System.out.println("Possible targets:");
                for (Player target : players) {
                    if (target == player)
                        continue;
                    System.out.print("[" + target.getId() + "] " + target.getName() + "\n");
                }
                targetPlayer = players.get(KeyboardInput.readInt() - 1);
                // System.out.print(targetPlayer.getName());
                if (player.getCard(cardToPlay) instanceof Bang)
                    playedBang = true;
                if (player.getCard(cardToPlay) instanceof Dynamite || player.getCard(cardToPlay) instanceof Prison) {
                    player.getCard(cardToPlay).action(player, targetPlayer, player.getCard(cardToPlay));
                    continue;
                }

                if (player.getCard(cardToPlay) instanceof CatBalou) {
                    if (targetPlayer.getHand().size() != 0) {
                        for (Card card : targetPlayer.getHand()) {
                            System.out.println("[" + (targetPlayer.getHand().indexOf(card) + 1) + "] Ruka");
                        }
                    }

                    if (targetPlayer.getBuffs().size() != 0) {
                        for (Card card : targetPlayer.getBuffs()) {
                            System.out
                                    .println("["
                                            + (targetPlayer.getHand().size() + targetPlayer.getBuffs().indexOf(card)
                                                    + 1)
                                            + "] " + card.getName());
                        }
                    }

                    if ((targetPlayer.getHand().size() + targetPlayer.getBuffs().size()) == 0) {
                        System.out.println("Player is out of cards.");
                        continue;
                    }

                    cardToSteal = KeyboardInput.readInt() - 1;

                    if (cardToSteal + 1 > targetPlayer.getHand().size()) {
                        player.getCard(cardToPlay).action(player, targetPlayer,
                                targetPlayer.getBuff(cardToSteal - targetPlayer.getHand().size()));
                    } else {
                        player.getCard(cardToPlay).action(player, targetPlayer, targetPlayer.getCard(cardToSteal));
                    }

                    discardCard(player, player.getCard(cardToPlay));
                    continue;
                }

                player.getCard(cardToPlay).action(targetPlayer);
                discardCard(player, player.getCard(cardToPlay));
            } else {
                if (player.getHealth() >= player.getHand().size())
                    break;
                System.out.println(
                        "Throw away " + (player.getHand().size() - player.getHealth()) + " card(s) to end turn.");
                for (Card card : player.getHand()) {
                    System.out.println("[" + (player.getHand().indexOf(card) + 1) + "] " + card.getName());
                }
                System.out.print("Card to discard: ");
                cardToDiscard = KeyboardInput.readInt() - 1;
                discardCard(player, player.getCard(cardToDiscard));
            }

        }

    }

    public boolean checkHealth(Player player) {
        if (player.getHealth() <= 0)
            return true;
        return false;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public List<Player> otherPlayers(Player player) {
        List<Player> newList = new ArrayList<>();
        for (Player elem : this.players) {
            if (elem != player) {
                newList.add(elem);
            }
        }
        return newList;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void startGame() {
        for (Player player : players) {
            dealCards(player, 3);
        }

        while (players.size() > 1) {
            for (Player player : players) {
                if (player.getHealth() <= 0)
                    continue;
                takeTurn(player);
            }
            Boolean killed;
            while (true) {
                killed = false;
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).getHealth() <= 0) {
                        players.remove(players.get(i));
                        killed = true;
                        break;
                    }
                }
                if (killed)
                    continue;
                break;
            }
        }

        CLS();
        System.out.println("VYHRAL " + players.get(0).getName());

        // the game is over, declare the winner here
    }

}
/*
 * This is just a basic outline, and you'll need to fill
 * in the details for each method.You'l l also need to
 * implement the effects of each type of card, as well as
 * handling the case where a player runs out of cards in
 * their deck and needs to shuffle the discard pile to
 * create a new deck.Good luck with your implementation
 * !
 */
