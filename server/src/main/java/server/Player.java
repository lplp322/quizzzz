package server;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private List<Joker> jokerList;

    /**
     * Default constructor
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.jokerList = new ArrayList<>();
    }

    /**
     * Constructor in case player has chosen special jokers
     * @param name
     * @param jokerList
     */
    public Player(String name, List<Joker> jokerList) {
        this.name = name;
        this.score = 0;
        this.jokerList = jokerList;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<Joker> getJokerList() {
        return jokerList;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", jokerList=" + jokerList +
                '}';
    }
}
