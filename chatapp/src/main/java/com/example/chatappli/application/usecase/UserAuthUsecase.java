package com.example.chatappli.application.usecase;

import com.example.chatappli.application.auth.UserAuthRepository;
import com.example.chatappli.application.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

//UserDetailsManagerこれを使用する、つまりユーザの登録や削除等を扱うユースケースを作成

@Service
@RequiredArgsConstructor
public class UserAuthUsecase {
    private final UserAuthRepository authRepository;

    public void userCreate(UserForm form, HttpServletRequest request) throws ServletException {
        authRepository.createUser(
                form.getUsername(),
                form.getPassword()
        );
    request.login(form.getUsername(),form.getPassword());
    }
}
//HttpServletRequestを使うことで、ユーザが問題なく作れたら自動でログインするようにしています。