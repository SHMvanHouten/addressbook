package com.github.shmvanhouten.addressbook.address;

import com.github.shmvanhouten.addressbook.DataBaseStructure;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressColumns.*;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.ADDRESS;

@Service
public class AddressStorageAdapterJdbcImpl implements AddressStorageAdapter {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AddressStorageAdapterJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> getAllAddresses() {
        String selectQuery = new SQL()
                .SELECT(ID)
                .SELECT(FIRST_NAME)
                .SELECT(SUR_NAME)
                .SELECT(STREET_NAME)
                .SELECT(HOUSE_NUMBER)
                .FROM(ADDRESS)
                .toString();

        return jdbcTemplate.query(selectQuery, new AddressRowMapper());
    }
}
