package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.AbstractJdbcRepositoryTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddressGroupRepositoryTest extends AbstractJdbcRepositoryTest {
    private AddressGroupRepository addressGroupRepository;

    @Before
    public void setUp() throws Exception {
        addressGroupRepository = new AddressGroupRepositoryJdbcImpl(namedParameterJdbcTemplate);
    }

    @Test
    public void itShouldMakeANewGroupAndDDeleteIt() throws Exception {
        String groupName = "testGroup";
        addressGroupRepository.addAddressGroup(groupName, Arrays.asList(1, 2, 4, 5));
        AddressGroup addressGroup = addressGroupRepository.getAddressGroup(groupName).get();
        assertThat(addressGroup.getName(), is(groupName));

        ExpectedException expectedException = ExpectedException.none();
        expectedException.expect(EmptyResultDataAccessException.class);
        addressGroupRepository.deleteGroup(groupName);
    }
}