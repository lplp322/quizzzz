package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.LobbyService;

@RestController
@RequestMapping("/lobby")
public class LobbyController {
    private LobbyService lobbyService;

    @Autowired
    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PutMapping("/connect/{name}")
    @ResponseBody
    public int playerConnects(@PathVariable String name) {
        lobbyService.addPlayer(name);
        return lobbyService.getIdCounter();
    }

    @PutMapping("/start/{type}")
    @ResponseBody
    public String startGame(@PathVariable String type) {
        int gameType = type.equals("singleplayer")?0:1;
        lobbyService.startGame(gameType);
        return "Started";
    }
}
