package com.github.shmvanhouten.addressbook.user;

import com.github.shmvanhouten.addressbook.AbstractJdbcRepositoryTest;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;

import java.util.List;
import java.util.Optional;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.USER;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.UserColumns.NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest extends AbstractJdbcRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepositoryJdbcImpl(namedParameterJdbcTemplate);
    }

    @Test
    public void itShouldGetAListOfAllUsers() throws Exception {
        List<User> users = userRepository.getAllUsers();

        assertThat(users.size(), greaterThan(1));
    }

    @Test
    public void itShouldAddANewUserAndDeleteIt() throws Exception {
        String testUser = "testUser";
        userRepository.addUser(testUser);

        User lastUser = getLatestAddedUser();

        assertThat(lastUser.getName(), is(testUser));
        int userId = lastUser.getId();

        userRepository.deleteUser(userId);

        Optional<User> deletedUser = userRepository.getUserById(userId);

        assertThat(deletedUser.isPresent(), is(false));


    }

    private User getLatestAddedUser() {
        String selectQuery = new SQL()
                .SELECT(ID)
                .SELECT(NAME)
                .FROM(USER)
                .WHERE(ID + " IN (" + new SQL()
                        .SELECT("MAX(" + ID + ") AS ID")
                        .FROM(USER) +")")
                .toString();
        return namedParameterJdbcTemplate.queryForObject(selectQuery, new EmptySqlParameterSource(), new UserRowMapper());
    }
}