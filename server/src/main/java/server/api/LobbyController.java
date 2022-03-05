package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.Game;
import server.LobbyService;

@RestController
@RequestMapping("/")
public class LobbyController {
    private LobbyService lobbyService;

    @Autowired
    public LobbyController(LobbyService lobbyService) {
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
        return 0;
    }

    /**
     * PUT request to start game, that is currently in lobby
     * @return - return trimmed game object !!!(Game will be changed to TrimmedGame later)
     */
    @PutMapping("/startGame")
    public Game startGame(){
        lobbyService.startGame(1);
        return lobbyService.getGameByID(lobbyService.getIdCounter()-1);
    }
}
