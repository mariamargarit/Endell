package dd.projects.ddshop.services;

import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.repos.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) { return addressRepository.save(address); }
    public List<Address> getAddresses() { return addressRepository.findAll(); }
    public void updateAddress(int addressId, Address newAddress) {
        Address address = addressRepository.findById(addressId).get();
        address.setCity(newAddress.getCity());
        address.setCountry(newAddress.getCountry());
        address.setCounty(newAddress.getCounty());
        address.setPostalCode(newAddress.getPostalCode());
        address.setStreetLine(newAddress.getStreetLine());
        addressRepository.save(address);
    }
    public void deleteAddressById(int id) { addressRepository.deleteById(id); }
}
