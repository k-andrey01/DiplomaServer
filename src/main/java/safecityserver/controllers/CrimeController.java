package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Crime;
import safecityserver.repos.CrimeRepo;

import java.util.Date;

@Controller
@RequestMapping(path="/crime")
public class CrimeController {
    @Autowired
    private CrimeRepo crimeRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewCrime (@RequestParam Date timeCrime, @RequestParam Date timeRecord, @RequestParam Integer typeId,
                        @RequestParam Integer addressId, @RequestParam Integer witnessId,
                        @RequestParam String comment) {
        Crime crime = new Crime();
        crime.setTimeCrime(timeCrime);
        crime.setTimeRecord(timeRecord);
        crime.setTypeId(typeId);
        crime.setAddressId(addressId);
        crime.setWitnessId(witnessId);
        crime.setComment(comment);
        crimeRepo.save(crime);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Crime> getAllCrime() {
        return crimeRepo.findAll();
    }
}