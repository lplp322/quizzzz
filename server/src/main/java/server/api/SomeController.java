package server.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SomeController {
    private int gameId=0;

    /**
     * Answering to the first client response, when new user is connecting to the game
     * @return gameID - game ID of this player
     */
    @GetMapping("/")
    @ResponseBody
    public String newPlayer() {
        gameId++;
        //if(currentGame.status()==true, then create new game with next gameID
        return "Your game id is: "+gameId;
    }

    /**
     * Posting the new score of this player
     * @param id - id of player
     * @param score - score is received from player and posted to database/player object
     * @return response from the server
     */
    @PostMapping("/playerScore/{id}")
    @ResponseBody
    public String postResult(@PathVariable int id, @RequestBody String score){
        System.out.println("Player with name "+ id + " has received: " + score);
        return "200 OK";
    }

    /**
     * Putting answer to the provided question of player with this id
     * @param id - id of this player
     * @param answer - answer(probably will be boolean or number)
     * @return response from the server
     */
    @PutMapping("/newAnswer/{id}")
    @ResponseBody
    public String putAnswer(@PathVariable int id, @RequestBody String answer){
        System.out.println("Player with name "+ id + " has answered: " + answer);
        boolean isCorrect = true; //hardcoded for now
        return "200 OK /n You answered: " + answer + " isCorrect: " + isCorrect;
    }

    /**
     * Deleting player with provided ID, if the player left game
     * @param id - id of this player
     * @return
     */
    @DeleteMapping("deletePlayer/{id}")
    @ResponseBody
    public String deletePlayer(@PathVariable int id){
        System.out.println("Player with name: " + id + " left the game and was deleted");
        return "200 OK";
    }
}