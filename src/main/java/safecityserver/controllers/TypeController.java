package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Type;
import safecityserver.repos.TypeRepo;

@Controller
@RequestMapping(path="/type")
public class TypeController {
    @Autowired
    private TypeRepo typeRepo;

    @PostMapping(path="/add")
    public @ResponseBody String addNewType (@RequestParam String typeName) {
        Type type = new Type();
        type.setNameType(typeName);
        typeRepo.save(type);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Type> getAllTypes() {
        return typeRepo.findAll();
    }
}