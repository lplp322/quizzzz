package server.api;

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
        a = new Activity("A", 100);
        b = new Activity("B", 99);
        c = new Activity("C", 103);
        d = new Activity("D", 104);
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
        assertEquals(1, lobbyService.getIdCounter());
        lobbyService.addPlayer("P1");
        lobbyService.startGame(0);
        assertEquals(2, lobbyService.getIdCounter());
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
        assertTrue(check.equals(lobbyService.getGameByID(0).getPlayers().get(0).getName()));
        assertEquals(3, lobbyService.getGameByID(1).getPlayers().size());
    }

}