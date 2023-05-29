package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Address;
import safecityserver.repos.AddressRepo;

import java.util.Optional;

@RestController
@RequestMapping(path="/address")
public class AddressController {
    @Autowired
    private AddressRepo addressRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    Integer addNewAddress (@RequestParam String city, @RequestParam String street,
                          @RequestParam Double coordX, @RequestParam Double coordY, @RequestParam String houseNumber) {
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setCoordX(coordX);
        address.setCoordY(coordY);
        address.setHouseNumber(houseNumber);
        addressRepo.save(address);
        return address.getId();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    @GetMapping(path="/select/{id}")
    public @ResponseBody Optional<Address> getAddressById(@PathVariable("id") Integer id) {
        return addressRepo.findById(id);
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateAddress(@PathVariable("id") Integer id, @RequestParam String city,
                                              @RequestParam String street, @RequestParam Double coordX, @RequestParam Double coordY,
                                              @RequestParam String houseNumber) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setCity(city);
            address.setStreet(street);
            address.setCoordX(coordX);
            address.setCoordY(coordY);
            address.setHouseNumber(houseNumber);
            addressRepo.save(address);
            return "Обновлено";
        } else {
            return "Адрес не найден";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteAddress(@PathVariable("id") Integer id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepo.delete(optionalAddress.get());
            return "Удалено";
        } else {
            return "Адрес не найден";
        }
    }
}