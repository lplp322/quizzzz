package server.api;

import commons.ActivityInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import server.Activity;

@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * Mapping to test how the ActivityInterface behaves
     * @return A new activity with the title test
     */
    @GetMapping("/testActivity")
    @ResponseBody
    public ActivityInterface newPlayer() {
            return new Activity("Test", 420, "testSource", "testPath");
    }
}
