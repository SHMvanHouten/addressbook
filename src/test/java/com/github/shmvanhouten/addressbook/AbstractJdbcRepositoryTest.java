package com.github.shmvanhouten.addressbook;

import org.junit.Before;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

import static com.github.shmvanhouten.addressbook.util.Password.DATABASE_PASSWORD;

public class AbstractJdbcRepositoryTest {
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Before
    public void setUpJdbcTemplate() throws Exception {
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/address?useSSL=false", "root", DATABASE_PASSWORD, true);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
