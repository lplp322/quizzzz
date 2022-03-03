package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.ActivityRepository;

import java.util.*;

@Service
public class LobbyService {
    private ActivityRepository dtBase;
    private Map<Integer, Game> games;
    private int idCounter;
    private List<Player> tempPlayers;
    private Set<String> names;

    @Autowired
    public LobbyService(ActivityRepository dtBase) {
        //System.out.println("ASDSADASDSA");
        this.dtBase = dtBase;
        games = new HashMap<>();
        idCounter = 0;
        tempPlayers = new ArrayList<>();
        names = new HashSet<>();
    }

    /**
     * Start the game by creating a game instance and adding it to the map of games
     * @param gameType Single-player or multi-player
     */
    public void startGame(int gameType) {
        Game tempGame = new Game(List.copyOf(tempPlayers), idCounter, gameType, dtBase);

        games.put(idCounter++, tempGame);
        tempPlayers = new ArrayList<>();
        names = new HashSet<>();

        System.out.println(tempGame);
    }

    /**
     * Adds the person with name {name} to the list of waiting players in the queue
     * @param name
     * @return true if player succesfully added
     */
    public boolean addPlayer(String name) {
        if(names.add(name)) {
            Player person = new Player(name);
            tempPlayers.add(person);
            return true;
        }
        return false;
    }

    public int getIdCounter() {
        return idCounter;
    }

    /**
     * Creating a game for one player
     * @param name - name of the player
     * @return id of the game
     */
    public int createSinglePlayerGame(String name) {
        Player person = new Player(name);
        List<Player> players = new ArrayList<>();
        players.add(person);
        Game newGame = new Game(players, idCounter, 0, dtBase);
        games.put(idCounter++, newGame);
        return idCounter-1;
    }

    /**
     * Return game object by this id
     * @param id - id of the game
     * @return game object with this id
     */
    public Game getGameByID(int id){
        return games.get(id);
    }
}