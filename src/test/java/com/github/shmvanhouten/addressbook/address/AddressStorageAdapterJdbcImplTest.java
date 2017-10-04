package com.github.shmvanhouten.addressbook.address;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

import java.util.List;

import static com.github.shmvanhouten.addressbook.util.Password.DATABASE_PASSWORD;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AddressStorageAdapterJdbcImplTest {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/address?useSSL=false", "root", DATABASE_PASSWORD, true);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Test
    public void itShouldGiveAListOfAllAddresses() throws Exception {
        AddressStorageAdapter addressStorageAdapter = new AddressStorageAdapterJdbcImpl(namedParameterJdbcTemplate);

        List<Address> addresses = addressStorageAdapter.getAllAddresses();

        assertThat(addresses.get(0).getFirstName(), is("Sjoerd"));
    }
}