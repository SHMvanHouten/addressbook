package com.github.shmvanhouten.addressbook.address;

import java.util.List;

public interface AddressRepository {

    public List<Address> getAllAddresses();

    Address getAddressForId(int id);

    void addAddress(Address address);

    List<Address> getAddressesForAddressGroup(int addressGroupId);
}
