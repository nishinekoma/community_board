package com.example.chatappli.application.form;
//個人追加
import com.example.chatappli.domain.model.checked.UniqueEmail;
import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotNull;//javax →　jakarta 以下同文
import com.example.chatappli.domain.type.MailAddress;//個人追加

import jakarta.annotation.Nullable;

import java.util.List;

@Data
public class MailForm {

    //htmlで別々のフォームに飛ばす（1ボタンで各オブジェクト）が無理だったのでこうする
    //ここにメアド追加してそれを掲示板でも使いたい！！！　自分で作ったやつ

    @Email//オブジェクトにつけるとエラー出たので String型にする
    @Nullable
    @UniqueEmail
    private  String mailAddress;//login.html signup69に合わせる

    //private List<MailForm> mailFormList;//重複チェック用メールアドレスのリスト

}
