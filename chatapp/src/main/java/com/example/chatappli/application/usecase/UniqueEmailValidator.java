package com.example.chatappli.application.usecase;

import com.example.chatappli.application.dto.MailReadDto;
import com.example.chatappli.domain.model.RelationUser_ID_Mail_Repository;
import com.example.chatappli.domain.model.checked.UniqueEmail;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

//@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private RelationUser_ID_Mail_Repository relationUserIdMailRepository;
    //バリデーション対象の変数の値を入れる
    private String email;
    //アノテーションクラスで設定しているデフォエラーメッセージ
    private String message;
    @Override
    public void initialize(UniqueEmail uniqueEmail) {
        this.message = uniqueEmail.message();
        System.out.println("log Constructor email:  " + email + "   similarly(同様に) " + message);//ログ
    }

    //チェックロジックを書く
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(" UniqueEmailValidator.isValid is started.");


        if(value == ""){
            System.out.println("nullだった");
            context.disableDefaultConstraintViolation();//デフォルトのメッセージ無効
            context.buildConstraintViolationWithTemplate("メールアドレスを登録してください。")
                    .addConstraintViolation();
            return false;
        }

        //重複チェック
        List<MailReadDto> dtos = relationUserIdMailRepository.selectByMail();
        System.out.println("value.toString for email: " + value);
        for(MailReadDto mailaddress : dtos){
            System.out.println("重複チェック開始"+mailaddress.getMailAddress());
                if(value.equals(mailaddress.getMailAddress()) == true) {//.getMailAddress()
                    //UniqueEmailのデフォルトメソッドが呼ばれるはず。　要検証。
                    System.out.println("重複あり");
                    context.disableDefaultConstraintViolation();//デフォルトのメッセージ無効
                    context.buildConstraintViolationWithTemplate("メールアドレスが重複しています。別のメールアドレスにしてください。")
                            .addConstraintViolation();
                    return false;
                }
            }
        return true;
    }
}
