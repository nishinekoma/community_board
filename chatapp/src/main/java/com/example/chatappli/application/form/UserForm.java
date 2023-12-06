package com.example.chatappli.application.form;

import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotNull;//javax →　jakarta 以下同文
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.example.chatappli.domain.type.MailAddress;//個人追加
@Data//ユーザログイン、登録のためのFormクラス　と　email初期化クラス
public class UserForm {
    @Size(max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")//ログインに使う名前とパスワードは半角英数字
    @NotNull
    private String username;


    //別のオブジェクトに移動
    /*htmlで別々のフォームに飛ばす（1ボタンで各オブジェクト）が無理だったのでこうする*/
    //ここにメアド追加してそれを掲示板でも使いたい！！！　自分で作ったやつ
    @NotNull
    private MailAddress mailAddress;//login.html signup69に合わせる

    @Size(max=64)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @NotNull
    private String password;
}
