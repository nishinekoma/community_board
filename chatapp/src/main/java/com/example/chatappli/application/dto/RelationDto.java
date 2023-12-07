package com.example.chatappli.application.dto;

import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.domain.model.UserComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RelationDto {
    private final String username;
    private final String mailAddress;
    private final String password;

    public static RelationDto from(UserForm userForm){ //sqlに入れるためにいUSERFORMをString型等に変更する。
        return new RelationDto(
                userForm.getUsername().toString(),
                userForm.getMailAddress().toString(),
                userForm.getPassword().toString()
        );
    }
}
