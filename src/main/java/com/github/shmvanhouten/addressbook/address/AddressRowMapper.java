package com.github.shmvanhouten.addressbook.address;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressColumns.*;
import static com.github.shmvanhouten.addressbook.address.Address.AddressBuilder.anAddress;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return anAddress()
                .withId(resultSet.getInt(ID))
                .withFirstName(resultSet.getString(FIRST_NAME))
                .withSurName(resultSet.getString(SUR_NAME))
                .withStreetName(resultSet.getString(STREET_NAME))
                .withHouseNumber(resultSet.getInt(HOUSE_NUMBER))
                .build();

    }
}
