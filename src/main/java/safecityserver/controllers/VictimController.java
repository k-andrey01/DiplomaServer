package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Crime;
import safecityserver.entities.Victim;
import safecityserver.repos.VictimRepo;

import java.util.Optional;

@RestController
@RequestMapping(path="/victim")
public class VictimController {
    @Autowired
    private VictimRepo victimRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewVictim (@RequestParam Integer age, @RequestParam String gender, @RequestParam Crime crime) {
        Victim victim = new Victim();
        victim.setAge(age);
        victim.setGender(gender);
        victim.setCrime(crime);
        victimRepo.save(victim);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Victim> getAllVictims() {
        return victimRepo.findAll();
    }

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Victim> getVictimById(@PathVariable("id") Integer id) {
        return victimRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateVictim(@PathVariable("id") Integer id, @RequestParam Integer age,
                                           @RequestParam String gender, @RequestParam Crime crime) {
        Optional<Victim> optionalVictim = victimRepo.findById(id);
        if (optionalVictim.isPresent()) {
            Victim victim = optionalVictim.get();
            victim.setAge(age);
            victim.setGender(gender);
            victim.setCrime(crime);
            victimRepo.save(victim);
            return "Updated";
        } else {
            return "Address not found";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteVictim(@PathVariable("id") Integer id) {
        Optional<Victim> optionalVictim = victimRepo.findById(id);
        if (optionalVictim.isPresent()) {
            victimRepo.delete(optionalVictim.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }

//    @PostMapping(path="/addPost")
//    public @ResponseBody
//    String addNewVictim (@RequestParam Integer age, @RequestParam String gender, @RequestParam Integer crimeId) {
//        Victim victim = new Victim();
//        victim.setAge(age);
//        victim.setGender(gender);
//        victim.setCrimeId(crimeId);
//        victimRepo.save(victim);
//        return "Saved";
//    }

//    @PutMapping(path="/updatePost/{id}")
//    public @ResponseBody String updateVictim (@PathVariable("id") Integer id, @RequestParam Integer age,
//                                              @RequestParam String gender, @RequestParam Integer crimeId) {
//        Optional<Victim> optionalVictim = victimRepo.findById(id);
//        if (optionalVictim.isPresent()) {
//            Victim victim = optionalVictim.get();
//            victim.setAge(age);
//            victim.setGender(gender);
//            victim.setCrimeId(crimeId);
//            victimRepo.save(victim);
//            return "Updated";
//        } else {
//            return "Address not found";
//        }
//    }
}
