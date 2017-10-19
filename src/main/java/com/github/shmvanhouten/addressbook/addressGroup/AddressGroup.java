package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.address.Address;

import java.util.List;
//Todo: instead field addresses holding a list of actual addresses, just let it hold the addressIds, addresses can be picked up later or combined with
// the list of all addresses in the gui.
public class AddressGroup {
    private int addressGroupId;
    private String name;

    private List<Address> addresses;

    public int getAddressGroupId() {
        return addressGroupId;
    }

    public void setAddressGroupId(int addressGroupId) {
        this.addressGroupId = addressGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public static final class AddressGroupBuilder {
        private int addressGroupId;
        private String name;
        private List<Address> addresses;

        private AddressGroupBuilder() {
        }

        public static AddressGroupBuilder anAddressGroup() {
            return new AddressGroupBuilder();
        }

        public AddressGroupBuilder withAddressGroupId(int addressGroupId) {
            this.addressGroupId = addressGroupId;
            return this;
        }

        public AddressGroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AddressGroupBuilder withAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public AddressGroup build() {
            AddressGroup addressGroup = new AddressGroup();
            addressGroup.setAddressGroupId(addressGroupId);
            addressGroup.setName(name);
            addressGroup.setAddresses(addresses);
            return addressGroup;
        }
    }
}
