package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.User;
import safecityserver.repos.UserRepo;

import java.util.Date;

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
}