package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.AbstractJdbcRepositoryTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddressGroupRepositoryTest extends AbstractJdbcRepositoryTest {
    private AddressGroupRepository addressGroupRepository;

    @Before
    public void setUp() throws Exception {
        addressGroupRepository = new AddressGroupRepositoryJdbcImpl(namedParameterJdbcTemplate);
    }

    @Test
    public void itShouldMakeANewGroupWithTwoAddresses() throws Exception {
        addressGroupRepository.addAddressGroup("prive", Arrays.asList(1,2));
        AddressGroup addressGroup = addressGroupRepository.getAddressGroup("prive").get();
    }
}