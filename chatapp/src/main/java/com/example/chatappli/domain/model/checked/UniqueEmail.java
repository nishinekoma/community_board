package com.example.chatappli.domain.model.checked;

import com.example.chatappli.application.usecase.UniqueEmailValidator;

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
    String message() default "Email address is already registered.";//validation失敗時にデフォルトメッセ表示。
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
