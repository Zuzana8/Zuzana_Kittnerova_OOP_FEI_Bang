package sk.stuba.fei.uim.oop.utility;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private Game game;
    Map<String, Integer> dict = new HashMap<String, Integer>();

    public Deck(Game game) {
        this.game = game;

        dict.put("Barrel", 2);// 2
        dict.put("Dynamite", 1);// 1
        dict.put("Prison", 3);// 3

        dict.put("Bang", 30);// 30
        dict.put("Dodge", 15);// 15
        dict.put("Beer", 8);// 8
        dict.put("Cat Balou", 6);// 6
        dict.put("Caravan", 4);// 4
        dict.put("Indians", 2);// 2

        createDeck(game);
        shuffle();
    }

    public void createDeck(Game game) { // cards.add(new Dynamite(game));
        String name;
        int count;
        for (Map.Entry<String, Integer> entry : dict.entrySet()) {
            name = entry.getKey();
            count = entry.getValue();
            switch (name) {
                case "Barrel":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Barrel(game));
                    }
                    ;
                    break;
                case "Dynamite":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Dynamite(game));
                    }
                    ;
                    break;
                case "Prison":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Prison(game));
                    }
                    ;
                    break;
                case "Bang":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Bang(game));
                    }
                    ;
                    break;
                case "Dodge":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Dodge(game));
                    }
                    ;
                    break;
                case "Beer":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Beer(game));
                    }
                    ;
                    break;
                case "Cat Balou":
                    for (int i = 0; i < count; i++) {
                        cards.add(new CatBalou(game));
                    }
                    ;
                    break;
                case "Caravan":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Stagecoach(game));
                    }
                    ;
                    break;
                case "Indians":
                    for (int i = 0; i < count; i++) {
                        cards.add(new Indians(game));
                    }
                    ;
                    break;
            }
        }
    }

    public List<Card> shuffle(List<Card> toShuffle) {
        Collections.shuffle(toShuffle);
        return toShuffle;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.size() == 0)
            discordPileToDeck();
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public Game getGame() {
        return game;
    }

    public void discordPileToDeck() {
        List<Card> newDeck = new ArrayList<>();
        newDeck = getGame().getDiscardPile();
        newDeck = shuffle(newDeck);
        cards = newDeck;
    }
}