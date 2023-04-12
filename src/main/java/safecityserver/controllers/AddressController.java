package safecityserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import safecityserver.entities.Address;
import safecityserver.repos.AddressRepo;

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
}