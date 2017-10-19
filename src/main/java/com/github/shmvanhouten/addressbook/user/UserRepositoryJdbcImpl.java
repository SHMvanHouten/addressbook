package com.github.shmvanhouten.addressbook.user;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.USER;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.NAME;
import static com.github.shmvanhouten.addressbook.util.CoalesceMaxUtil.coalesceMax;
import static com.github.shmvanhouten.addressbook.util.NamedParamUtil.namedParam;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String selectQuery = new SQL()
                .SELECT(ID)
                .SELECT(NAME)
                .FROM(USER)
                .ORDER_BY(NAME)
                .toString();

        return jdbcTemplate.query(selectQuery, new UserRowMapper());
    }

    @Override
    public void addUser(String userName) {
        String insertStatement = new SQL()
                .INSERT_INTO(USER)
                .VALUES(ID, namedParam(ID))
                .VALUES(NAME, namedParam(NAME))
                .toString();

        SqlParameterSource params = new MapSqlParameterSource(ID, getNextId())
                .addValue(NAME, userName);

        jdbcTemplate.update(insertStatement, params);
    }

    private int getNextId() {
        String selectQuery = new SQL()
                .SELECT(coalesceMax(ID))
                .FROM(USER)
                .toString();
        return jdbcTemplate.queryForObject(selectQuery, new EmptySqlParameterSource(), Integer.class) + 1;
    }

}
