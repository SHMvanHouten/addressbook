package com.github.shmvanhouten.addressbook.addressGroup;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressGroupColumns.*;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressGroupIdentifiersColumns.*;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.ADDRESS_GROUP;
import static com.github.shmvanhouten.addressbook.addressGroup.NewAddressGroupIdentifiers.AddressGroupIdentifiersBuilder.anAddressGroupIdentifiers;
import static com.github.shmvanhouten.addressbook.util.CoalesceMaxUtil.coalesceMax;
import static com.github.shmvanhouten.addressbook.util.NamedParamUtil.namedParam;

@Repository
public class AddressGroupRepositoryJdbcImpl implements AddressGroupRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AddressGroupRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAddressGroup(String groupName, List<Integer> addressIds) {
        if (addressIds.isEmpty()) {
            addAddressGroup(groupName);
        } else {
            addAddressGroupWithAddresses(groupName, addressIds);
        }
    }

    @Override
    public Optional<AddressGroup> getAddressGroup(String groupName) {
        String selectQuery = new SQL()
                .SELECT(ID)
                .SELECT(ADDRESS_GROUP_NAME)
                .FROM(ADDRESS_GROUP)
                .WHERE(ADDRESS_GROUP_NAME + " = " + namedParam(ADDRESS_GROUP_NAME))
                .toString();
        SqlParameterSource param = new MapSqlParameterSource(ADDRESS_GROUP_NAME, groupName);
        return Optional.of(jdbcTemplate.queryForObject(selectQuery, param, new AddressGroupRowMapper()));
    }

    private void addAddressGroupWithAddresses(String groupName, List<Integer> addressIds) {
        String insertStatement = new SQL()
                .INSERT_INTO(ADDRESS_GROUP)
                .VALUES(ID, namedParam(ID))
                .VALUES(ADDRESS_GROUP_ID, namedParam(ADDRESS_GROUP_ID))
                .VALUES(ADDRESS_GROUP_NAME, namedParam(ADDRESS_GROUP_NAME))
                .VALUES(ADDRESS_GROUP_SEQUENCE, namedParam(ADDRESS_GROUP_SEQUENCE))
                .VALUES(ADDRESS_ID, namedParam(ADDRESS_ID))
                .toString();

        NewAddressGroupIdentifiers newAddressGroupIdentifiers = getAddressGroupIdentifiers();
        MapSqlParameterSource groupParams = new MapSqlParameterSource(ADDRESS_GROUP_ID, newAddressGroupIdentifiers.getAddressGroupId())
                .addValue(ADDRESS_GROUP_NAME, groupName);

        for (Integer addressId : addressIds) {
            addAddressToAddressGroup(addressId, insertStatement, groupParams, newAddressGroupIdentifiers);
            newAddressGroupIdentifiers.incrementIdAndSequence();
        }
    }

    private void addAddressToAddressGroup(Integer addressId, String insertStatement, MapSqlParameterSource params, NewAddressGroupIdentifiers newAddressGroupIdentifiers) {

        params.addValue(ID, newAddressGroupIdentifiers.getId())
                .addValue(ADDRESS_GROUP_SEQUENCE, newAddressGroupIdentifiers.getSequence())
                .addValue(ADDRESS_ID, addressId);

        jdbcTemplate.update(insertStatement, params);
    }

    private NewAddressGroupIdentifiers getAddressGroupIdentifiers() {
        String selectQuery = new SQL()
                .SELECT(MAX_UNITED_ID)
                .SELECT(MAX_UNITED_GROUP_ID)
                .FROM("(" + buildMaxIdQuery() + " UNION " + buildMaxGroupIdQuery() + ")" + AS_UNITED)
                .toString();

        return jdbcTemplate.queryForObject(selectQuery, new EmptySqlParameterSource(), new NewAddressGroupIdentifiersRowMapper());
    }

    private String buildMaxIdQuery() {
        return new SQL()
                .SELECT(coalesceMax(ID) + AS_ID)
                .SELECT("-1" + AS_GROUP_ID)
                .FROM(ADDRESS_GROUP)
                .toString();
    }

    private String buildMaxGroupIdQuery() {
        return new SQL()
                .SELECT("-1" + AS_ID)
                .SELECT(coalesceMax(ADDRESS_GROUP_ID) + AS_GROUP_ID)
                .FROM(ADDRESS_GROUP)
                .toString();
    }

    private void addAddressGroup(String groupName) {

    }


    private class NewAddressGroupIdentifiersRowMapper implements RowMapper<NewAddressGroupIdentifiers> {
        @Override
        public NewAddressGroupIdentifiers mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return anAddressGroupIdentifiers()
                    .withId(resultSet.getInt(MAX_UNITED_ID) + 1)
                    .withAddressGroupId(resultSet.getInt(MAX_UNITED_GROUP_ID) + 1)
                    .withSequence(1)
                    .build();
        }
    }
}
