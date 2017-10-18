package com.github.shmvanhouten.addressbook.address;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressColumns.*;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.ADDRESS;
import static com.github.shmvanhouten.addressbook.util.CoalesceMaxUtil.coalesceMax;
import static com.github.shmvanhouten.addressbook.util.NamedParamUtil.namedParam;

@Repository
public class AddressRepositoryJdbcImpl implements AddressRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AddressRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate) {
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

    @Override
    public Address getAddressForId(int id) {

        String selectQuery = new SQL()
                .SELECT(ID)
                .SELECT(FIRST_NAME)
                .SELECT(SUR_NAME)
                .SELECT(STREET_NAME)
                .SELECT(HOUSE_NUMBER)
                .FROM(ADDRESS)
                .WHERE(ID + " = " + namedParam(ID))
                .toString();

        SqlParameterSource param = new MapSqlParameterSource(ID, id);

        return jdbcTemplate.queryForObject(selectQuery, param, new AddressRowMapper());
    }

    @Override
    public void addAddress(Address address) {

        int nextId = getNextId();

        String insertStatement = new SQL()
                .INSERT_INTO(ADDRESS)
                .VALUES(ID, namedParam(ID))
                .VALUES(FIRST_NAME, namedParam(FIRST_NAME))
                .VALUES(SUR_NAME, namedParam(SUR_NAME))
                .VALUES(STREET_NAME, namedParam(STREET_NAME))
                .VALUES(HOUSE_NUMBER, namedParam(HOUSE_NUMBER))
                .toString();

        SqlParameterSource params = new MapSqlParameterSource(ID, nextId)
                .addValue(FIRST_NAME, address.getFirstName())
                .addValue(SUR_NAME, address.getSurName())
                .addValue(STREET_NAME, address.getStreetName())
                .addValue(HOUSE_NUMBER, address.getHouseNumber());

        jdbcTemplate.update(insertStatement, params);
    }

    @Override
    public List<Address> getAddressesForAddressGroup(int addressGroupId) {
        return null;
    }

    private int getNextId() {
        String selectQuery = new SQL()
                .SELECT( coalesceMax(ID) )
                .FROM(ADDRESS)
                .toString();

        return jdbcTemplate.queryForObject(selectQuery, new EmptySqlParameterSource(), Integer.class) + 1;
    }
}
