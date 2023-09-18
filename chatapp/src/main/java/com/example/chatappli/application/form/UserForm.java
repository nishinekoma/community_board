package com.example.chatappli.application.form;

import lombok.Data;

import jakarta.validation.constraints.NotNull;//javax →　jakarta 以下同文
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data//ユーザログイン、登録のためのFormクラス
public class UserForm {
    @Size(max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")//ログインに使う名前とパスワードは半角英数字
    @NotNull
    private String username;
    @Size(max=64)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @NotNull
    private String password;
}
