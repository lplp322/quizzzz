package commons;

public class LeaderboardEntry {
    private String name;
    private int score;

    /**
     * @param name the name of the player in the leaderboard
     * @param score the score of the player in the leaderboard
     */
    public LeaderboardEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return returns the name of the player in that leaderboard entry
     */
    public String getName() {
        return name;
    }

    /**
     * @return the score of teh player in that entry of the leaderboard
     */
    public int getScore() {
        return score;
    }

    /**
     * @param name sets the name of that entry in the leaderboard
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param score sets the score for that entry in the leaderboard
     */
    public void setScore(int score) {
        this.score = score;
    }
}
