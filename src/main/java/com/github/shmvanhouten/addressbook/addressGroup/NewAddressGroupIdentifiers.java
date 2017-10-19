package com.github.shmvanhouten.addressbook.addressGroup;

public class NewAddressGroupIdentifiers {

    private final int addressGroupId;

    private int id;
    private int sequence;

    public NewAddressGroupIdentifiers(int addressGroupId) {
        this.addressGroupId = addressGroupId;
    }

    public void incrementIdAndSequence() {
        id++;
        sequence++;
    }


    public int getAddressGroupId() {
        return addressGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }



    public static final class AddressGroupIdentifiersBuilder {
        private int addressGroupId;
        private int id;
        private int sequence;

        private AddressGroupIdentifiersBuilder() {
        }

        public static AddressGroupIdentifiersBuilder anAddressGroupIdentifiers() {
            return new AddressGroupIdentifiersBuilder();
        }

        public AddressGroupIdentifiersBuilder withAddressGroupId(int addressGroupId) {
            this.addressGroupId = addressGroupId;
            return this;
        }

        public AddressGroupIdentifiersBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public AddressGroupIdentifiersBuilder withSequence(int sequence) {
            this.sequence = sequence;
            return this;
        }

        public NewAddressGroupIdentifiers build() {
            NewAddressGroupIdentifiers newAddressGroupIdentifiers = new NewAddressGroupIdentifiers(addressGroupId);
            newAddressGroupIdentifiers.setId(id);
            newAddressGroupIdentifiers.setSequence(sequence);
            return newAddressGroupIdentifiers;
        }
    }
}
