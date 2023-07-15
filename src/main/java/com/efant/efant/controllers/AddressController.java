package com.efant.efant.controllers;

import com.efant.efant.model.entities.Address;
import com.efant.efant.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable Long id) throws Exception {
        return addressService.getAddressById(id);
    }


    @PostMapping("/address")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address address) {
        address = addressService.createAddress(address);
        return address;
    }

    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address) throws Exception {
        // validate
        if (!id.equals(address.getAddressId())) {
            throw new Exception("ID in path and ID in body are not the same");
        }

        address = addressService.updateAddress(address);
        return address;
    }


    @DeleteMapping("/address/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id) throws Exception {
        addressService.deleteAddress(id);
    }


}
