package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.AbstractJdbcRepositoryTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressGroupRepositoryTest extends AbstractJdbcRepositoryTest {
    private AddressGroupRepository addressGroupRepository;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        addressGroupRepository = new AddressGroupRepositoryJdbcImpl(namedParameterJdbcTemplate);
    }

    @Test
    public void itShouldGetAListOfAllAddressGroups() throws Exception {
        List<AddressGroup> addressGroups = addressGroupRepository.getAllAddressGroups();
        assertThat(addressGroups.get(0).getName(), is("prive"));
        assertThat(addressGroups.get(1).getName(), is("favoriteShops"));
    }

    @Test
    public void itShouldMakeANewGroupAndDDeleteIt() throws Exception {
        String groupName = "testGroup";
        addressGroupRepository.addAddressGroup(groupName, Arrays.asList(1, 2, 4, 5));
        List<AddressGroup> addressGroups = addressGroupRepository.getAllAddressGroups();
        AddressGroup lastAddressGroup = addressGroups.get(addressGroups.size() - 1);
        assertThat(lastAddressGroup.getName(), is(groupName));
        int lastGroupId = lastAddressGroup.getAddressGroupId();
        System.out.println(lastGroupId);

        addressGroupRepository.deleteGroup(lastGroupId);

        expectedException.expect(EmptyResultDataAccessException.class);
        addressGroupRepository.getAddressGroup(lastGroupId);
    }

}