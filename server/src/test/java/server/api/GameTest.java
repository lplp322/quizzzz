package server.api;

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

public class GameTest {
    @Mock
    private ActivityRepository repo;
    private List<Player> players;
    private Game game;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ArrayList<Activity> acts = new ArrayList<>();
        acts.add(new Activity("A", 100));
        acts.add(new Activity("B", 99));
        acts.add(new Activity("C", 50));
        acts.add(new Activity("D", 70));
        Mockito.when(repo.getAllActivities()).thenReturn(acts);
        players = new ArrayList<>();
        players.add(new Player("player1", null));
        players.add(new Player("player2", null));
        game = new Game(players, 0, 2, repo);
    }

    @Test
    public void noJokerTrim() {
        TrimmedGame trim = new TrimmedGame(0, game.getQuestions().get(0).getQuestion(), 20, 20, game.getQuestions().get(0).getAnswers());
        assertEquals(trim, game.trim(new Player("test")));
    }
}
