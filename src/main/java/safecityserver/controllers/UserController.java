package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.User;
import safecityserver.repos.UserRepo;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewUser (@RequestParam String login, @RequestParam String password, @RequestParam String name,
                       @RequestParam String surname, @RequestParam Date birthdate,
                       @RequestParam String gender) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthdate(birthdate);
        user.setGender(gender);
        userRepo.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateUser(@PathVariable("id") Integer id, @RequestParam String login, @RequestParam String password,
                                              @RequestParam String name, @RequestParam String surname, @RequestParam Date birthdate,
                                              @RequestParam String gender) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
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
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.delete(optionalUser.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }
}