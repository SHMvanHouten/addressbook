package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressGroupService {

    private final AddressGroupRepository addressGroupRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public AddressGroupService(AddressGroupRepository addressGroupRepository, AddressRepository addressRepository) {
        this.addressGroupRepository = addressGroupRepository;
        this.addressRepository = addressRepository;
    }

    Optional<AddressGroup> getAddressGroup(Integer groupId) {
        Optional<AddressGroup> addressGroupOptional = addressGroupRepository.getAddressGroup(groupId);
        if(addressGroupOptional.isPresent()){
            AddressGroup addressGroup = addressGroupOptional.get();
            addressGroup.setAddresses(addressRepository.getAddressesForAddressGroup(addressGroup.getAddressGroupId()));
            return Optional.of(addressGroup);
        }
        return Optional.empty();
    }

    public List<AddressGroup> getAddressGroupList() {
        List<AddressGroup> addressGroups = addressGroupRepository.getAllAddressGroups();
        /*Todo: choose to either return a list of filled out addressGroups or addressGroups without the addresses and
        * ask to fill it out from the gui when an addressGroup is selected*/
//        for (AddressGroup emptyAddressGroup : addressGroups) {
//            emptyAddressGroup.setAddresses(addressRepository.getAddressesForAddressGroup(emptyAddressGroup.getAddressGroupId()));
//        }
        return addressGroups;
    }
}
