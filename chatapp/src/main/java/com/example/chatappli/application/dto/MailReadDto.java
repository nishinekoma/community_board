package com.example.chatappli.application.dto;
import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.domain.type.MailAddress;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MailReadDto {
    private final String mailAddress;

    public static List<MailReadDto> from(List<MailForm> mailFormList){//String型のメアドが格納されてる
        //MailFormのリスト
       return mailFormList.stream().map(
                mailForm -> new MailReadDto(
                        mailForm.getMailAddress()
                )
        ).collect(Collectors.toUnmodifiableList());//変更不可リスト
    }
}
