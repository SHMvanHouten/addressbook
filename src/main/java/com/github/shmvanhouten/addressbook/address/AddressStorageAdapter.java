package com.github.shmvanhouten.addressbook.address;

import java.util.List;

public interface AddressStorageAdapter {

    public List<Address> getAllAddresses();

    Address getAddressForId(int id);
}
