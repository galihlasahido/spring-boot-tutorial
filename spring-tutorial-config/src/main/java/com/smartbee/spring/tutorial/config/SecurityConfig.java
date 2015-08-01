package com.smartbee.spring.tutorial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

/**
 * Created by TheArtOfMovement on 6/29/15.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Secured(value = "enabled")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
        auth.userDetailsService(jdbcDao()).passwordEncoder(encoder);
    }

    public JdbcDaoImpl jdbcDao() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setEnableAuthorities(Boolean.FALSE);
        jdbcDao.setEnableGroups(Boolean.TRUE);
        jdbcDao.setUsersByUsernameQuery("SELECT USERNAME, PASSWORD, ACTIVE FROM MEMBER WHERE UPPER(USERNAME) = UPPER(?) ");
        jdbcDao.setGroupAuthoritiesByUsernameQuery("SELECT R.ID_ROLES, R.NAME AS GROUP_NAME, P.VALUE AS AUTHORITY " +
                "FROM USERS U INNER JOIN ROLES R ON U.ID_ROLES = R.ID_ROLES INNER JOIN " +
                "ROLES_PERMISSION RP ON RP.ID_ROLES = R.ID_ROLES INNER JOIN " +
                "PERMISSION P ON RP.ID_PERMISSION = P.ID_PERMISSION " +
                "WHERE UPPER(U.USERNAME) = UPPER(?) ");
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/css/**")
            .antMatchers("/font/**")
            .antMatchers("/img/**")
            .antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/user-login**").permitAll()
                .antMatchers("/denied**").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest()
                .anonymous()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied")
                .and()
                .rememberMe().and()
                .formLogin()
                .loginPage("/user-login")
                .defaultSuccessUrl("/dashboard/static")
                .failureUrl("/error-login")
                .permitAll()
                .and()
                .logout()
                .and()
                .sessionManagement()
                .maximumSessions(1);
    }


}
