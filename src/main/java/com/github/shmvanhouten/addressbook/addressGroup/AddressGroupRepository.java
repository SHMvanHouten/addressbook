package com.github.shmvanhouten.addressbook.addressGroup;

import java.util.List;
import java.util.Optional;

public interface AddressGroupRepository {

    public void addAddressGroup(String groupName, List<Integer> addressIds);

    Optional<AddressGroup> getAddressGroup(String groupName);

    void deleteGroup(String groupName);
}
