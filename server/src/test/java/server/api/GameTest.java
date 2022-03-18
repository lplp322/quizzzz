package server.api;

// CHECKSTYLE:OFF
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Activity;
import server.Game;
import server.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private Game game;
    private TestActivityRepository activityRepository;

    @BeforeEach
    public void init() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("A", 2, "DAS", "DAS"));
        activities.add(new Activity("B", 3, "DAS", "DAS"));
        activities.add(new Activity("C", 4, "DAS", "DAS"));
        activities.add(new Activity("D", 5, "DAS", "DAS"));
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
    @Test
    public void testThreadTick() {
        Thread tickThread = new Thread(game::run);
        tickThread.start();
        int currTimer = game.getRound().getTimer();
        try {
            TimeUnit.SECONDS.sleep(1);
            assertEquals(currTimer - 1, game.getRound().getTimer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
