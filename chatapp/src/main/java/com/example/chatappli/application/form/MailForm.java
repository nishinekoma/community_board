package com.example.chatappli.application.form;
//個人追加
import com.example.chatappli.domain.model.checked.UniqueEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotNull;//javax →　jakarta 以下同文
import com.example.chatappli.domain.type.MailAddress;//個人追加

import jakarta.annotation.Nullable;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@UniqueEmail(email = "mailAddress")//作成したアノテーションと入力チェックを行うフィールド
public class MailForm {

    //htmlで別々のフォームに飛ばす（1ボタンで各オブジェクト）が無理だったのでこうする
    //ここにメアド追加してそれを掲示板でも使いたい！！！　自分で作ったやつ

    @Email//オブジェクトにつけるとエラー出たので String型にする
    @Valid
    private String mailAddress;//login.html signup69に合わせる

}
