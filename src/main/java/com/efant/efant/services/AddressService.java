package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.Address;
import com.efant.efant.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new EfantException("ADDRESS_NOT_FOUND", "Address not exists with id: " + id, HttpStatus.NOT_FOUND));
    }


    public Address createAddress(Address address) throws Exception {
        if (address.getAddressId() != null) {
            throw new EfantException("NEW_ADDRESS_ID_IS_NOT_NULL", "Address id must be null", HttpStatus.BAD_REQUEST);
        }
        address = addressRepository.save(address);
        return address;
    }

    public Address updateAddress(Address address) throws Exception {
        Long addressId = address.getAddressId();
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new EfantException("ADDRESS_NOT_FOUND", "Address not exists with id: " + addressId, HttpStatus.NOT_FOUND));

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
            throw new EfantException("ADDRESS_NOT_FOUND", "Address not exists with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
