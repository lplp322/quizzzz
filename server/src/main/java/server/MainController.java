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
    /**
     * Start the game in single player mode
     * @param name - name of the player
     * @return return id of the game
     */
    @GetMapping("/singleplayer/{name}")
    public int startSinglePlayer(@PathVariable String name){
        return lobbyService.createSinglePlayerGame(name);
    }

    /**
     * Return game info object(now full game object sent just to check)
     * @param gameID - id of the game
     * @return game info object
     */
    @GetMapping("{gameID}/getGameInfo")
    public Game getGameInfo(@PathVariable int gameID){
        return lobbyService.getGameByID(gameID);
    }

    /**
     * //PROBLEM WITH IDs FOUND DURING TESTING(needed to be solved)
     * Adding new player to the lobby of multiplayer game
     * @param name - name of the player
     * @return - id of the game
     */
    @GetMapping("/multiplayer/{name}")
    public int addNewUserMultiPlayer(@PathVariable String name){
        if(lobbyService.addPlayer(name)){
            return lobbyService.getIdCounter();
        }
        return -1;
    }
}
