package com.github.shmvanhouten.addressbook.addressGroup;

import com.github.shmvanhouten.addressbook.address.Address;
import com.github.shmvanhouten.addressbook.address.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.github.shmvanhouten.addressbook.address.Address.AddressBuilder.anAddress;
import static com.github.shmvanhouten.addressbook.addressGroup.AddressGroup.AddressGroupBuilder.anAddressGroup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressGroupHandlerTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressGroupRepository addressGroupRepository;

    @Test
    public void itShouldGetAnAddressGroupForTheNameFriends() throws Exception {
        AddressGroupHandler addressGroupHandler = new AddressGroupHandler(addressGroupRepository, addressRepository);

        final String groupName = "friends";

        Optional<AddressGroup> addressGroupOptional = Optional.of(anAddressGroup()
                .withAddressGroupId(3)
                .withName(groupName)
                .build());

        when(addressGroupRepository.getAddressGroup(groupName)).thenReturn(addressGroupOptional);

        List<Address> addresses = Collections.singletonList(anAddress()
                .withId(123)
                .withFirstName("John")
                .withSurName("Doe")
                .build());

        when(addressRepository.getAddressesForAddressGroup(3)).thenReturn(addresses);

        AddressGroup addressGroup = addressGroupHandler.getAddressGroup(groupName).get();

        assertThat(addressGroup.getAddressGroupId(), is(3));
        assertThat(addressGroup.getAddresses().get(0).getFirstName(), is("John"));
    }
}