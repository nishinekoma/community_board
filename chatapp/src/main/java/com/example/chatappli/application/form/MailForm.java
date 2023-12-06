package com.example.chatappli.application.form;
//個人追加
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Data;

import jakarta.validation.constraints.NotNull;//javax →　jakarta 以下同文
import com.example.chatappli.domain.type.MailAddress;//個人追加
@Data
public class MailForm {

    //htmlで別々のフォームに飛ばす（1ボタンで各オブジェクト）が無理だったのでこうする
    //ここにメアド追加してそれを掲示板でも使いたい！！！　自分で作ったやつ
    @NotNull
    private MailAddress mailAddress;//login.html signup69に合わせる

}
