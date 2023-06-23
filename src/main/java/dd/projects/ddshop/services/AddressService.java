package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.entities.Address;
import dd.projects.ddshop.entities.Product;
import dd.projects.ddshop.entities.Subcategory;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.repos.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Address createAddress(AddressDTO addressDTO) {
        return addressRepository.save(addressMapper.toAddress(addressDTO));
    }
    public List<AddressDTO> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toAddressDTO)
                .collect(toList());
    }
    public void updateAddress(int addressId, AddressDTO newAddressDTO) {
        Address address = addressRepository.findById(addressId).get();
        address.setCity(newAddressDTO.getCity());
        address.setCountry(newAddressDTO.getCountry());
        address.setCounty(newAddressDTO.getCounty());
        address.setPostalCode(newAddressDTO.getPostalCode());
        address.setStreetLine(newAddressDTO.getStreetLine());
        addressRepository.save(address);
    }
    public void deleteAddressById(int id) { addressRepository.deleteById(id); }
}
