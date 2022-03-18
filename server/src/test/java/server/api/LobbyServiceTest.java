package server.api;

//CHECKSTYLE:OFF
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import server.Activity;
import server.LobbyService;
import server.database.ActivityRepository;


import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class LobbyServiceTest {
    private Activity a,b,c,d;
    @Autowired
    private ActivityRepository dtBase;

    @BeforeEach
    public void setup(){
        a = new Activity("A", 100, "DAS", "DAS");
        b = new Activity("B", 99, "DAS", "DAS");
        c = new Activity("C", 103, "DAS", "DAS");
        d = new Activity("D", 104, "DAS", "ASD");
        dtBase.save(a);
        dtBase.save(b);
        dtBase.save(c);
        dtBase.save(d);
    }
    @Test
    void startGame() {
        LobbyService lobbyService = new LobbyService(dtBase);
        lobbyService.addPlayer("P1");
        lobbyService.addPlayer("P2");
        lobbyService.addPlayer("P3");
        lobbyService.startGame(1);
        assertEquals(2, lobbyService.getIdCounter());
        lobbyService.addPlayer("P1");
        lobbyService.startGame(0);
        assertEquals(3, lobbyService.getIdCounter());
    }

    @Test
    void createSinglePlayerGame() {
        LobbyService lobbyService = new LobbyService(dtBase);
        lobbyService.addPlayer("P1");
        lobbyService.addPlayer("P2");
        lobbyService.addPlayer("P3");
        lobbyService.createSinglePlayerGame("Ivan");
        lobbyService.startGame(1);
        String check = "Ivan";
        assertTrue(check.equals(lobbyService.getGameByID(-1).getPlayers().get("Ivan").getName()));
        assertEquals(3, lobbyService.getGameByID(1).getPlayers().size());
    }
    @Test
    void checkPlayerMap(){
        LobbyService lobbyService = new LobbyService(dtBase);
        lobbyService.addPlayer("P1");
        lobbyService.addPlayer("P2");
        lobbyService.addPlayer("P3");
        lobbyService.createSinglePlayerGame("Ivan");
        lobbyService.startGame(1);
        assertTrue(lobbyService.getGameByID(1).getPlayers().get("P1").getName().equals("P1"));
    }
}