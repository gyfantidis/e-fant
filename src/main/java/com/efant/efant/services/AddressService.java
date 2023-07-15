package com.efant.efant.services;

import com.efant.efant.model.entities.Address;
import com.efant.efant.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) throws Exception {
        return addressRepository.findById(id)
                .orElseThrow(() -> new Exception("Address not exists with id: " + id));

    }


    public Address createAddress(Address address) {
        address = addressRepository.save(address);
        return address;
    }

    public Address updateAddress(Address address) throws Exception {
        Long addressId = address.getAddressId();
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new Exception("Address not exists with id: " + addressId));

        // Update the properties of the existingAddress with the values from the updated address
        existingAddress.setAddress(address.getAddress());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setAddressNumber(address.getAddressNumber());
        existingAddress.setFloor(address.getFloor());
        existingAddress.setRingName(address.getRingName());
        existingAddress.setComments(address.getComments());


        // Save the updated address entity
        existingAddress = addressRepository.save(existingAddress);

        return existingAddress;

    }


    public void deleteAddress(Long id) throws Exception {
        Address address = addressRepository.findById(id).orElse(null);

        if (address != null) {
            addressRepository.deleteById(id);
        } else {
            throw new Exception("Address not exists with id:" + id);
        }
    }


}
