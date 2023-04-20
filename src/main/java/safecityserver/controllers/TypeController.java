package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Type;
import safecityserver.repos.TypeRepo;

import java.util.Optional;

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

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Type> getTypeById(@PathVariable("id") Integer id) {
        return typeRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateType(@PathVariable("id") Integer id, @RequestParam String nameType) {
        Optional<Type> optionalType = typeRepo.findById(id);
        if (optionalType.isPresent()) {
            Type type = optionalType.get();
            type.setNameType(nameType);
            typeRepo.save(type);
            return "Updated";
        } else {
            return "Type not found";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteType(@PathVariable("id") Integer id) {
        Optional<Type> optionalType = typeRepo.findById(id);
        if (optionalType.isPresent()) {
            typeRepo.delete(optionalType.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }
}