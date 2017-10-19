package com.github.shmvanhouten.addressbook.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.NAME;
import static com.github.shmvanhouten.addressbook.user.User.UserBuilder.aUser;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return aUser()
                .withId(resultSet.getInt(ID))
                .withName(resultSet.getString(NAME))
                .build();
    }
}
