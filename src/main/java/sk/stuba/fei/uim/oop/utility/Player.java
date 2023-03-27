package sk.stuba.fei.uim.oop.utility;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private int id;
    private int health;
    private List<Card> hand;
    private Boolean inPrison = false;
    private List<Card> buffs;
    private Game game;

    public Player(String name, int id, Game game) {
        this.game = game;
        this.name = name;
        this.id = id;
        this.health = 4;
        this.hand = new ArrayList<>();
        this.buffs = new ArrayList<>();
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int newHealth) {
        if (newHealth < 0)
            newHealth = 0;
        this.health = newHealth;
    }

    public List<Card> getHand() {
        return this.hand;
    }

    public void removeCard(Card card) {
        getGame().discardCard(this, card);
        this.hand.remove(card);
    }

    public Card getCard(int index) {
        if (index == -1) {
            return null;
        }
        if (index >= this.hand.size() || index < 0) {
            System.out.println("Wrong option.");
            return null;
        }
        return this.hand.get(index);
    }

    public Boolean inPrison() {
        return this.inPrison;
    }

    public void putInPrison() {
        this.inPrison = true;
    }

    public void releaseFromPrison() {
        this.inPrison = false;
    }

    public List<Card> getBuffs() {
        return this.buffs;
    }

    public Card getBuff(int index) {
        return this.buffs.get(index);
    }

    public void addBuff(Card card) {
        this.buffs.add(card);
    }

    public void removeBuff(Card card) {
        getGame().discardCard(this, card);
        this.buffs.remove(card);
    }

}
