package com.example.chatappli.application.form;

import lombok.Data;//getter setterのやつ
import org.hibernate.validator.constraints.Length;//文字列の長さバリデーション：入力された文字列の長さが指定された範囲内にあるかをチェック。
import org.springframework.lang.Nullable;//メソッドの引数や戻り値がＮｕｌｌだった場合許容するため。

//import javax.validation.constraints.Email;//文字列が正しいメールアドレスのフォーマット（書式）であるかを確認する。　有効なメアドかもチェックする。
import jakarta.validation.constraints.Email;
//import javax.validation.constraints.NotNull;//フィールドや引数Nullチェック。
import jakarta.validation.constraints.NotNull;
//public @interface ExtendedEmailValidatorに使うやつ。
import javax.validation.constraints.Pattern;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

//バリデーションロジックに必要なクラス。
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Data
public class CommentForm {
	@Nullable
	@Length(max=20)
	private String name = null;
	
	//@ExtendedEmailValidator jakartaにしたら＠Email動作したのでよい。
	//signupから登録された物が代入される。
	@Email
	@Nullable
	private String mailAddress;
	
	@NotNull
	@Length(min=1, max=400)
	private String comment = "defo";
	
	/*@Emailが機能しないので正規表現を定義した　@ExtendedEmailValidatorを定義する　参考　https://stackoverflow.com/questions/50535214/javax-validation-constraints-email-matching-invalid-email-address*/
	
	@Email(message="Please provide a valid email address")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)//実行時にVMに読み込まれる https://qiita.com/kashira2339/items/6450714e42c37b441514
	@Constraint(validatedBy = ExtendedEmailValidatorImpl.class)
	@Documented
	@ReportAsSingleViolation
	public @interface ExtendedEmailValidator{
		String message() default "Please provide a valid email address";
		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {};
	}
	public class ExtendedEmailValidatorImpl implements ConstraintValidator<ExtendedEmailValidator, String> {

	    @Override
	    public void initialize(ExtendedEmailValidator constraintAnnotation) {
	        // 初期化処理
	    	
	    }

	    @Override
	    public boolean isValid(String value, ConstraintValidatorContext context) {
	        // バリデーションロジックを実装
	        // 正しいメールアドレスの形式かどうかをチェックし、結果を返す
	        return value != null && value.matches(".+@.+\\..+");
	    }
	}
}