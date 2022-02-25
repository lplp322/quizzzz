package server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SomeController {
    private int gameId = 0;
    //create new Game object
    @GetMapping("/")
    @ResponseBody
    public String newPlayer() {
        gameId++;
        //if(currentGame.status()==true, then create new game with next gameID
        return "Your game id is: "+gameId;
    }

    @PostMapping("/playerScore/{id}")
    @ResponseBody
    public String postResult(@PathVariable int id, @RequestBody String score){
        System.out.println("Player with name "+ id + " has received: " + score);
        return "200 OK";
    }

    @PutMapping("/newAnswer/{id}")
    @ResponseBody
    public String putAnswer(@PathVariable int id, @RequestBody String answer){
        System.out.println("Player with name "+ id + " has answered: " + answer);
        return "200 OK";
    }
    
    @DeleteMapping("deletePlayer/{id}")
    @ResponseBody
    public String deletePlayer(@PathVariable int id){
        System.out.println("Player with name: " + id + " left the game and was deleted");
        return "200 OK";
    }
}