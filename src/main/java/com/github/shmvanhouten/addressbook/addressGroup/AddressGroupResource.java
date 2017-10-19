package com.github.shmvanhouten.addressbook.addressGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "addressBook")
public class AddressGroupResource {

    private final AddressGroupService addressGroupService;

    @Autowired
    public AddressGroupResource(AddressGroupService addressGroupService) {
        this.addressGroupService = addressGroupService;
    }

    @RequestMapping(value = "all", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<AddressGroup> getAllAddressGroups(){
        return addressGroupService.getAddressGroupList();
    }
}
