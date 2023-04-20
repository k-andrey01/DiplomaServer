package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Address;
import safecityserver.repos.AddressRepo;

import java.util.Optional;

@Controller
@RequestMapping(path="/address")
public class AddressController {
    @Autowired
    private AddressRepo addressRepo;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewAddress (@RequestParam String city, @RequestParam String district, @RequestParam String street,
                          @RequestParam Double coordX, @RequestParam Double coordY, @RequestParam String houseNumber) {
        Address address = new Address();
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setCoordX(coordX);
        address.setCoordY(coordY);
        address.setHouseNumber(houseNumber);
        addressRepo.save(address);
        return "Saved";
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
    public @ResponseBody String updateAddress(@PathVariable("id") Integer id, @RequestParam String city, @RequestParam String district,
                                              @RequestParam String street, @RequestParam Double coordX, @RequestParam Double coordY,
                                              @RequestParam String houseNumber) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setCity(city);
            address.setDistrict(district);
            address.setStreet(street);
            address.setCoordX(coordX);
            address.setCoordY(coordY);
            address.setHouseNumber(houseNumber);
            addressRepo.save(address);
            return "Updated";
        } else {
            return "Address not found";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteAddress(@PathVariable("id") Integer id) {
        Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepo.delete(optionalAddress.get());
            return "Deleted";
        } else {
            return "Address not found";
        }
    }


}