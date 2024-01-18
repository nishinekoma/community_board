package com.example.chatappli.domain.model.checked;

import com.example.chatappli.application.usecase.UniqueEmailValidator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})//このアノテーションはクラスに対してのみ使用できる。
@Retention(RetentionPolicy.RUNTIME)//実行時にアノテーション利用可能にする
@Documented//Javadoc
@Constraint(validatedBy = UniqueEmailValidator.class)//制約の実装クラス指定
public @interface UniqueEmail {
    String message() default "Email address is already registered.";//validation失敗時にデフォルトメッセ表示。
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
