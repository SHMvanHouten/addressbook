package com.github.shmvanhouten.addressbook.user;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.USER;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.NAME;

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
}
