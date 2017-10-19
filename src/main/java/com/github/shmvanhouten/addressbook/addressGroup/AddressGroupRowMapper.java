package com.github.shmvanhouten.addressbook.addressGroup;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressGroupColumns.ADDRESS_GROUP_ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressGroupColumns.ADDRESS_GROUP_NAME;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressGroupColumns.ID;
import static com.github.shmvanhouten.addressbook.addressGroup.AddressGroup.AddressGroupBuilder.anAddressGroup;

public class AddressGroupRowMapper implements RowMapper<AddressGroup> {
    @Override
    public AddressGroup mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return anAddressGroup()
                .withAddressGroupId(resultSet.getInt(ADDRESS_GROUP_ID))
                .withName(resultSet.getString(ADDRESS_GROUP_NAME))
                .build();
    }
}
