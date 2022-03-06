package server.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Activity;
import server.Game;
import server.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game;
    private TestActivityRepository activityRepository;

    @BeforeEach
    public void init() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("A", 2));
        activities.add(new Activity("B", 3));
        activities.add(new Activity("C", 4));
        activities.add(new Activity("D", 5));
        activityRepository = new TestActivityRepository(activities);
        game = new Game(List.of(
                    new Player("A"),
                    new Player("B")
                ),
                1, 1, activityRepository);
    }

    @Test
    public void testGeneral() {
        assertEquals(20, game.getQuestions().size());
    }
    @Test
    public void testPlayers() {
        assertEquals("A", game.getPlayers().get(0).getName());
    }
    @Test
    public void testGameType() {
        assertEquals(1, game.getGameType());
    }
    @Test
    public void testGameId() {
        assertEquals(1, game.getLobbyId());
    }
}
