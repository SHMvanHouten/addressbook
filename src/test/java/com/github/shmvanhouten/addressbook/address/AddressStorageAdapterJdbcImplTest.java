package com.github.shmvanhouten.addressbook.address;

import com.github.shmvanhouten.addressbook.DataBaseStructure;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

import java.util.List;

import static com.github.shmvanhouten.addressbook.DataBaseStructure.AddressColumns.ID;
import static com.github.shmvanhouten.addressbook.DataBaseStructure.Table.ADDRESS;
import static com.github.shmvanhouten.addressbook.address.Address.AddressBuilder.anAddress;
import static com.github.shmvanhouten.addressbook.util.NamedParamUtil.namedParam;
import static com.github.shmvanhouten.addressbook.util.Password.DATABASE_PASSWORD;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AddressStorageAdapterJdbcImplTest {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private AddressStorageAdapter addressStorageAdapter;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/address?useSSL=false", "root", DATABASE_PASSWORD, true);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        addressStorageAdapter = new AddressStorageAdapterJdbcImpl(namedParameterJdbcTemplate);
    }

    @Test
    public void itShouldGiveAListOfAllAddresses() throws Exception {

        List<Address> addresses = addressStorageAdapter.getAllAddresses();

        assertThat(addresses.get(0).getStreetName(), is("Kalverstraat"));
    }

    @Test
    public void itShouldGiveAnAddressById() throws Exception {
        int id = 1;

        final String actualStreetName = addressStorageAdapter.getAddressForId(id).getStreetName();

        assertThat(actualStreetName, is("Kalverstraat"));
    }

    @Test
    public void itShouldAddANewAddressToTheDatabase() throws Exception {
        Address addressToInsert = makeAddressToInsert();

        addressStorageAdapter.addAddress(addressToInsert);

        List<Address> addresses = addressStorageAdapter.getAllAddresses();
        Address lastAddedAddress = addresses.get(addresses.size() - 1);

        System.out.println(lastAddedAddress.getId());

        assertThat(lastAddedAddress.getStreetName(), is("Main Street"));

        deleteAddress(lastAddedAddress.getId());
    }

    private void deleteAddress(int id) {
        String deleteStatement = new SQL()
                .DELETE_FROM(ADDRESS)
                .WHERE(ID + " = " + namedParam(ID))
                .toString();
        SqlParameterSource param = new MapSqlParameterSource(ID, id);

        namedParameterJdbcTemplate.update(deleteStatement, param);
    }

    private Address makeAddressToInsert() {
        return anAddress()
                .withFirstName("John")
                .withSurName("Doe")
                .withStreetName("Main Street")
                .withHouseNumber(1)
                .build();
    }
}