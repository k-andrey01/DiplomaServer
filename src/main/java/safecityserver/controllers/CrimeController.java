package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.*;
import safecityserver.repos.CrimeRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path="/crime")
public class CrimeController {
    @Autowired
    private CrimeRepo crimeRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    Integer addNewCrime (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime timeCrime,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime timeRecord,
                         @RequestParam Type type, @RequestParam Address address, @RequestParam Userr witness,
                         @RequestParam String comment) {
        Crime crime = new Crime();
        crime.setTimeCrime(timeCrime);
        crime.setTimeRecord(timeRecord);
        crime.setType(type);
        crime.setAddress(address);
        crime.setWitness(witness);
        crime.setComment(comment);
        crimeRepo.save(crime);
        return crime.getId();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Crime> getAllCrime() {
        return crimeRepo.findAll();
    }

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Crime> getCrimeById(@PathVariable("id") Integer id) {
        return crimeRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateCrime(@PathVariable("id") Integer id, @RequestParam LocalDateTime timeCrime,
                                            @RequestParam LocalDateTime timeRecord, @RequestParam Type type,
                                            @RequestParam Address address, @RequestParam Userr witness,
                                            @RequestParam String comment) {
        Optional<Crime> optionalCrime = crimeRepo.findById(id);
        if (optionalCrime.isPresent()) {
            Crime crime = optionalCrime.get();
            crime.setTimeCrime(timeCrime);
            crime.setTimeRecord(timeRecord);
            crime.setType(type);
            crime.setAddress(address);
            crime.setWitness(witness);
            crime.setComment(comment);
            crimeRepo.save(crime);
            return "Обновлено";
        } else {
            return "Опасность не найдена";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteCrime(@PathVariable("id") Integer id) {
        Optional<Crime> optionalCrime = crimeRepo.findById(id);
        if (optionalCrime.isPresent()) {
            crimeRepo.delete(optionalCrime.get());
            return "Удалено";
        } else {
            return "Опасность не найдена";
        }
    }
}