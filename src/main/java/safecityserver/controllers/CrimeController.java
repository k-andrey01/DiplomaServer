package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.dto.CrimesForMapDto;
import safecityserver.entities.*;
import safecityserver.repos.CrimeRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @GetMapping(path="/countByKind")
    @ResponseBody
    public Map<String, Long> getCountByKind() {
        List<Crime> crimes = crimeRepo.findAll();
        Map<String, Long> countByKind = new HashMap<>();
        for (Crime crime : crimes) {
            String kind = crime.getType().getKind();
            countByKind.put(kind, countByKind.getOrDefault(kind, 0L) + 1);
        }
        return countByKind;
    }

    @GetMapping(path="/allForMap")
    public @ResponseBody Iterable<CrimesForMapDto> getAllCrimesForMap(){
        Iterable<Crime> crimes = crimeRepo.findAll();
        List<CrimesForMapDto> crimesForMapDtos = new ArrayList<>();

        for (Crime crime: crimes){
            CrimesForMapDto dto = new CrimesForMapDto();
            dto.setId(crime.getId());
            dto.setCoordX(crime.getAddress().getCoordX());
            dto.setCoordY(crime.getAddress().getCoordY());
            dto.setTimeCrime(crime.getTimeCrime());
            dto.setComment(crime.getComment());
            dto.setCity(crime.getAddress().getCity());
            dto.setStreet(crime.getAddress().getStreet());
            dto.setHouse(crime.getAddress().getHouseNumber());
            dto.setType(crime.getType().getNameType());
            dto.setKind(crime.getType().getKind());
            dto.setVictims(crime.getVictims());

            crimesForMapDtos.add(dto);
        }

        return crimesForMapDtos;
    }

    @GetMapping(path="/allByWitness/{login}")
    public @ResponseBody Iterable<CrimesForMapDto> getAllCrimesForMap(@PathVariable("login") String login){
        Iterable<Crime> crimes = crimeRepo.findByWitnessLogin(login);
        List<CrimesForMapDto> crimesForMapDtos = new ArrayList<>();

        for (Crime crime: crimes){
            CrimesForMapDto dto = new CrimesForMapDto();
            dto.setId(crime.getId());
            dto.setCoordX(crime.getAddress().getCoordX());
            dto.setCoordY(crime.getAddress().getCoordY());
            dto.setTimeCrime(crime.getTimeCrime());
            dto.setComment(crime.getComment());
            dto.setCity(crime.getAddress().getCity());
            dto.setStreet(crime.getAddress().getStreet());
            dto.setHouse(crime.getAddress().getHouseNumber());
            dto.setType(crime.getType().getNameType());
            dto.setKind(crime.getType().getKind());
            dto.setVictims(crime.getVictims());

            crimesForMapDtos.add(dto);
        }

        return crimesForMapDtos;
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