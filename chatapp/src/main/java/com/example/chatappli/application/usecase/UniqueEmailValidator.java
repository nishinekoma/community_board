package com.example.chatappli.application.usecase;

import com.example.chatappli.domain.model.checked.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;  // UserRepositoryは実際のリポジトリに合わせて変更

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // 初期化処理（特に何もしない）
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userRepository.existsByMailAddress(email);
    }
}
