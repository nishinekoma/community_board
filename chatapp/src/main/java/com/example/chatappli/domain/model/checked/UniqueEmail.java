package com.example.chatappli.domain.model.checked;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email address is already registered.";

    Class<?>[] groups() default {};//デフォルトメソッド定義　返り値実装先のクラス型

    Class<? extends Payload>[] payload() default {};
}