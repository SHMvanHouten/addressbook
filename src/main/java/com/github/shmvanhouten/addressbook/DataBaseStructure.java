package com.github.shmvanhouten.addressbook;

public class DataBaseStructure {
    public class Table {

        public static final String ADDRESS = "address";
        public static final String ADDRESS_GROUP = "addressGroup";
    }

    public class AddressColumns {

        public static final String ID = "address.ID";
        public static final String FIRST_NAME = "firstName";
        public static final String SUR_NAME = "surName";
        public static final String STREET_NAME = "streetName";
        public static final String HOUSE_NUMBER = "houseNumber";

    }

    public class AddressGroupColumns {

        public static final String ID = "id";
        public static final String ADDRESS_GROUP_ID = "addressGroupId";
        public static final String ADDRESS_GROUP_NAME = "addressGroupName";
        public static final String ADDRESS_GROUP_SEQUENCE = "addressGroupSequence";
        public static final String ADDRESS_ID = "addressId";
    }

    public class AddressGroupIdentifiersColumns {
        public static final String MAX_UNITED_ID = "MAX(united.id)";
        public static final String MAX_UNITED_GROUP_ID = "MAX(united.groupId)";
        public static final String MAX_UNITED_SEQUENCE = "MAX(united.sequence)";
        public static final String AS_ID = " AS id";
        public static final String AS_GROUP_ID = " AS groupId";
        public static final String AS_SEQUENCE = " AS sequence";
        public static final String AS_UNITED = " AS united";
    }

}
