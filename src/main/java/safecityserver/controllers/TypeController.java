package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Type;
import safecityserver.repos.TypeRepo;

import java.util.*;

@Controller
@RequestMapping(path="/type")
public class TypeController {
    @Autowired
    private TypeRepo typeRepo;

    @PostMapping(path="/add")
    public @ResponseBody String addNewType (@RequestParam String typeName, @RequestParam String kind) {
        Type type = new Type();
        type.setNameType(typeName);
        type.setKind(kind);
        typeRepo.save(type);
        return "Добавлено";
    }
    
    @GetMapping(path="/getByType")
    public @ResponseBody Integer getTypeId (@RequestParam String typeName){
        Optional<Type> type = typeRepo.findByNameType(typeName);
        Type currentType = type.get();
        return currentType.getId();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Type> getAllTypes() {
        return typeRepo.findAll();
    }

    @GetMapping(path="/allNames")
    public @ResponseBody List<String> getAllTypeNames() {
        List<Type> types = typeRepo.findAll();
        List<String> typeNames = new ArrayList<>();
        for (Type type : types) {
            typeNames.add(type.getNameType());
        }
        return typeNames;
    }



    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Type> getTypeById(@PathVariable("id") Integer id) {
        return typeRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateType(@PathVariable("id") Integer id, @RequestParam String nameType,
                                           @RequestParam String kind) {
        Optional<Type> optionalType = typeRepo.findById(id);
        if (optionalType.isPresent()) {
            Type type = optionalType.get();
            type.setNameType(nameType);
            type.setKind(kind);
            typeRepo.save(type);
            return "Обновлено";
        } else {
            return "Тип не найден";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteType(@PathVariable("id") Integer id) {
        Optional<Type> optionalType = typeRepo.findById(id);
        if (optionalType.isPresent()) {
            typeRepo.delete(optionalType.get());
            return "Удалено";
        } else {
            return "Тип не найден";
        }
    }
}