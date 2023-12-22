package com.example.chatappli.application.usecase;

import com.example.chatappli.application.dto.MailReadDto;
import com.example.chatappli.application.dto.UserCommentReadDto;
import com.example.chatappli.domain.model.checked.UniqueEmail;
import com.example.chatappli.infrastructure.datasource.UserCommentDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    UserCommentDatasource userCommentDatasource;
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // 初期化処理（特に何もしない）
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        //emailが入力されなかったとき
        if(email == null){
            context.disableDefaultConstraintViolation();//デフォルトのメッセージ無効
            context.buildConstraintViolationWithTemplate("メールアドレスを登録してください。")
                    .addConstraintViolation();
            return false; //null無効
        }

        //重複チェック
        List<MailReadDto> dtos = userCommentDatasource.selectByMail();
        for(MailReadDto mailaddress : dtos){
                if(email.equals(mailaddress.getMailAddress()) == true) return false;
            }

        return false;
    }
}
