package com.example.chatappli.application.auth;
import com.example.chatappli.domain.type.MailAddress;//個人追加
public interface UserAuthRepository {
    void createUser(String userName,String password);
}
