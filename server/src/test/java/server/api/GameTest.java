package server.api;
//CHECKSTYLE:OFF
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import server.Activity;
import server.Game;
import server.Player;
import commons.TrimmedGame;
import server.database.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameTest {
    @Mock
    private ActivityRepository repo;
    private List<Player> players;
    private Game game;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ArrayList<Activity> acts = new ArrayList<>();
        acts.add(new Activity("A", 2));
        acts.add(new Activity("B", 3));
        acts.add(new Activity("C", 4));
        acts.add(new Activity("D", 5));
        Mockito.when(repo.getAllActivities()).thenReturn(acts);
        players = new ArrayList<>();
        players.add(new Player("A", null));
        players.add(new Player("B", null));
        game = new Game(players, 1, 1, repo);
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

    @Test
    public void noJokerTrim() {
        TrimmedGame trim = new TrimmedGame(1, game.getQuestions().get(0).getQuestion(), 20, 20,
                game.getQuestions().get(0).getAnswers(), game.getQuestions().get(0).getType());
        TrimmedGame gameTrim = game.trim();
        assertEquals(trim, gameTrim);
    }
}