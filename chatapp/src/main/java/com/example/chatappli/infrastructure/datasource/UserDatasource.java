package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.application.auth.UserAuthRepository;
import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;//authority 権力、権限
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import com.example.chatappli.domain.type.MailAddress;//個人追加
@RequiredArgsConstructor
@Repository
public class UserDatasource implements UserAuthRepository {
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String userName,String password) {//ここで重複チェックすべき？
        User user = new User(//imp UserDetails
                userName,
                passwordEncoder.encode(password),
                ImmutableSet.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        manager.createUser(user);//user = imp UserDetails

        //UserDetailsManager  void createUser(UserDetails user);
        /*
        application.ymlにこう書いてあるので　managerは　 JdbcUserDetailsManager　がDIされる。
        schema-locations: #初期化時に実行されるスキーマのSQLファイルパス
                - classpath:h2/schema.sql #
        - classpath:org/springframework/security/core/userdetails/jdbc/users.ddl
        で、email受け取れるようにUser.javaいじろうと思ったけどリードオンリーだったためあきらめー
         */
    }
}
