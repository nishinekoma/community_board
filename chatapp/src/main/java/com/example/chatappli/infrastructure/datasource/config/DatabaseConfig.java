package com.example.chatappli.infrastructure.datasource.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;//https://inero-software.com/spring-boot-3-0-migration-overview/   一部JakartaEEではなくJDK17に依存しているのでjakartaにリファクタリングする必要がない。

@Configuration//Configrationクラスの提供　
@RequiredArgsConstructor
public class DatabaseConfig {
    private final DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager() {//jdbcユーザー管理サービス
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}