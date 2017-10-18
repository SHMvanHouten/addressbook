package com.github.shmvanhouten.addressbook.addressGroup;

import java.util.List;

public interface AddressGroupRepository {

    public void addAddressGroup(String groupName, List<Integer> addressIds);
}
