package com.example.chatappli.application.usecase;

import com.example.chatappli.application.dto.MailReadDto;
import com.example.chatappli.application.dto.UserCommentReadDto;
import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.domain.model.RelationUser_ID_Mail_Repository;
import com.example.chatappli.domain.model.checked.UniqueEmail;
import com.example.chatappli.infrastructure.datasource.UserCommentDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private RelationUser_ID_Mail_Repository relationUserIdMailRepository;
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // 初期化処理（特に何もしない）
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        //emailが入力されなかったとき
        System.out.println(email);
        if(email.isEmpty()){
            System.out.println("nullだった");
            context.disableDefaultConstraintViolation();//デフォルトのメッセージ無効
            context.buildConstraintViolationWithTemplate("メールアドレスを登録してください。")
                    .addConstraintViolation();
            return false; //null無効
        }

        //重複チェック
        List<MailReadDto> dtos = relationUserIdMailRepository.selectByMail();
        for(MailReadDto mailaddress : dtos){
                if(email.equals(mailaddress.getMailAddress()) == true) {
                    //UniqueEmailのデフォルトメソッドが呼ばれるはず。　要検証。
                    System.out.println("重複あり");
                    return false;
                }
            }
        return true;
    }
}
