package com.github.shmvanhouten.addressbook.user;

import com.github.shmvanhouten.addressbook.AbstractJdbcRepositoryTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class UserRepositoryTest extends AbstractJdbcRepositoryTest{

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
}