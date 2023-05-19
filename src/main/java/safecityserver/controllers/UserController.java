package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Userr;
import safecityserver.repos.UserRepo;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String login, @RequestParam String password, @RequestParam String name,
                       @RequestParam String surname, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,
                       @RequestParam String gender) {
        Userr user = new Userr();
        user.setLogin(login);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        //user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthdate(birthdate);
        user.setGender(gender);
        userRepo.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Userr> getAllUser() {
        return userRepo.findAll();
    }

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Userr> getUserById(@PathVariable("id") Integer id) {
        return userRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateUser(@PathVariable("id") Integer id, @RequestParam String login, @RequestParam String password,
                                           @RequestParam String name, @RequestParam String surname, @RequestParam Date birthdate,
                                           @RequestParam String gender) {
        Optional<Userr> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            Userr user = optionalUser.get();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthdate(birthdate);
            user.setGender(gender);
            userRepo.save(user);
            return "Updated";
        } else {
            return "Address not found";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUser(@PathVariable("id") Integer id) {
        Optional<Userr> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.delete(optionalUser.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }
}