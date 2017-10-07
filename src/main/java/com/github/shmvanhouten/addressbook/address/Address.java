package com.github.shmvanhouten.addressbook.address;

public class Address {
    private int id;

    private String firstName;
    private String surName;

    private String streetName;
    private int houseNumber;
    private String houseAdditional;

    private String city;
    private String country;
    private String postCode;

    private String email;
    private int phoneNumber;

    private String additionalNotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseAdditional() {
        return houseAdditional;
    }

    public void setHouseAdditional(String houseAdditional) {
        this.houseAdditional = houseAdditional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }


    public static final class AddressBuilder {
        private int id;
        private String firstName;
        private String surName;
        private String streetName;
        private int houseNumber;
        private String houseAdditional;
        private String city;
        private String country;
        private String postCode;
        private String email;
        private int phoneNumber;
        private String additionalNotes;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AddressBuilder withSurName(String surName) {
            this.surName = surName;
            return this;
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder withHouseAdditional(String houseAdditional) {
            this.houseAdditional = houseAdditional;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public AddressBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public AddressBuilder withPhoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public AddressBuilder withAdditionalNotes(String additionalNotes) {
            this.additionalNotes = additionalNotes;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setId(id);
            address.setFirstName(firstName);
            address.setSurName(surName);
            address.setStreetName(streetName);
            address.setHouseNumber(houseNumber);
            address.setHouseAdditional(houseAdditional);
            address.setCity(city);
            address.setCountry(country);
            address.setPostCode(postCode);
            address.setEmail(email);
            address.setPhoneNumber(phoneNumber);
            address.setAdditionalNotes(additionalNotes);
            return address;
        }
    }
}
