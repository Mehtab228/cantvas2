package com.cantvas2.cantvas2.config;

import javax.sql.DataSource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.cantvas2.cantvas2.models.CantvasUser;

public class StringlyTypedJdbcUserDetailsManager extends JdbcUserDetailsManager {
    
    public StringlyTypedJdbcUserDetailsManager(DataSource ds) {
        setDataSource(ds);
    }

    @Override
    public void createUser(UserDetails userDetails) {
        CantvasUser user = (CantvasUser) userDetails;
        getJdbcTemplate().update("insert into users (username, password, enabled, user_type) values (?, ?, ?, ?)", (ps) -> {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            ps.setString(4, user.getUserType());
        });
    }
}
