package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Victims;
import safecityserver.repos.VictimsRepo;

@Controller
@RequestMapping(path="/victims")
public class VictimsController {
    @Autowired
    private VictimsRepo victimsRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewVictims (@RequestParam Integer age, @RequestParam String gender, @RequestParam Integer crimeId) {
        Victims victim = new Victims();
        victim.setAge(age);
        victim.setGender(gender);
        victim.setCrimeId(crimeId);
        victimsRepo.save(victim);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Victims> getAllVictims() {
        return victimsRepo.findAll();
    }
}
