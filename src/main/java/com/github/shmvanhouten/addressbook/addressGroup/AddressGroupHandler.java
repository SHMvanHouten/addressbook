package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressGroupHandler {

    private final AddressGroupRepository addressGroupRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressGroupHandler(AddressGroupRepository addressGroupRepository, AddressRepository addressRepository) {
        this.addressGroupRepository = addressGroupRepository;
        this.addressRepository = addressRepository;
    }

    Optional<AddressGroup> getAddressGroup(String groupName) {
        Optional<AddressGroup> addressGroupOptional = addressGroupRepository.getAddressGroup(groupName);
        if(addressGroupOptional.isPresent()){
            AddressGroup addressGroup = addressGroupOptional.get();
            addressGroup.setAddresses(addressRepository.getAddressesForAddressGroup(addressGroup.getAddressGroupId()));
            return Optional.of(addressGroup);
        }
        return Optional.empty();
    }
}
