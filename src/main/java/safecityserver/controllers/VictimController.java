package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Crime;
import safecityserver.entities.Victim;
import safecityserver.repos.VictimRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return "Пострадавший добавлен";
    }

    @GetMapping(path="/countByGender")
    @ResponseBody
    public Map<String, Long> getCountByGender() {
        List<Victim> victims = victimRepo.findAll();
        Map<String, Long> countByGender = new HashMap<>();
        for (Victim victim : victims) {
            String gender = victim.getGender();
            countByGender.put(gender, countByGender.getOrDefault(gender, 0L) + 1);
        }
        return countByGender;
    }

    @GetMapping("/countByAgeGroup")
    @ResponseBody
    public Map<String, Long> getCountByAgeGroup() {
        List<Victim> victims = victimRepo.findAll();
        Map<String, Long> countByAgeGroup = new HashMap<>();

        for (Victim victim : victims) {
            int age = victim.getAge();
            String ageGroup = getAgeGroup(age);

            countByAgeGroup.put(ageGroup, countByAgeGroup.getOrDefault(ageGroup, 0L) + 1);
        }

        return countByAgeGroup;
    }

    private String getAgeGroup(int age) {
        if (age <= 11) {
            return "0-11";
        } else if (age <= 17) {
            return "12-17";
        } else if (age <= 35) {
            return "18-35";
        } else if (age <= 60) {
            return "36-60";
        } else {
            return "61+";
        }
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
            return "Обновлено";
        } else {
            return "Пострадавший не найден";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteVictim(@PathVariable("id") Integer id) {
        Optional<Victim> optionalVictim = victimRepo.findById(id);
        if (optionalVictim.isPresent()) {
            victimRepo.delete(optionalVictim.get());
            return "Удаленл";
        } else {
            return "Пострадавший не найден";
        }
    }
}
