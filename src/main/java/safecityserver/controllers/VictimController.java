package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Victim;
import safecityserver.repos.VictimRepo;

import java.util.Optional;

@Controller
@RequestMapping(path="/victim")
public class VictimController {
    @Autowired
    private VictimRepo victimRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewVictims (@RequestParam Integer age, @RequestParam String gender, @RequestParam Integer crimeId) {
        Victim victim = new Victim();
        victim.setAge(age);
        victim.setGender(gender);
        victim.setCrimeId(crimeId);
        victimRepo.save(victim);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Victim> getAllVictims() {
        return victimRepo.findAll();
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateVictims(@PathVariable("id") Integer id, @RequestParam Integer age,
                                           @RequestParam String gender, @RequestParam Integer crimeId) {
        Optional<Victim> optionalVictim = victimRepo.findById(id);
        if (optionalVictim.isPresent()) {
            Victim victim = optionalVictim.get();
            victim.setAge(age);
            victim.setGender(gender);
            victim.setCrimeId(crimeId);
            victimRepo.save(victim);
            return "Updated";
        } else {
            return "Address not found";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteVictims(@PathVariable("id") Integer id) {
        Optional<Victim> optionalVictim = victimRepo.findById(id);
        if (optionalVictim.isPresent()) {
            victimRepo.delete(optionalVictim.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }
}
