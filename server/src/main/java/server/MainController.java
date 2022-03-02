package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    private LobbyService lobbyService;

    @Autowired
    public MainController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    /**
     * Start singleplayer game with provided name
     * @param name - name of the player
     * @return - id of the singleplayer game
     */
    @GetMapping("/singleplayer/{name}")
    public int startSinglePlayer(@PathVariable String name){
        return lobbyService.createSinglePlayerGame(name);
    }

    @GetMapping("{gameID}/getGameInfo")
    public Game getGameInfo(@PathVariable int gameID){
        return lobbyService.getGameByID(gameID);
    }

}
