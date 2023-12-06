package com.example.chatappli.application.usecase;


import com.example.chatappli.application.form.CommentForm;
import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.domain.model.*;
import org.springframework.security.core.userdetails.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class RelationUserID_Mail {
    private String MailAddress;
    //個人追加
    @Autowired
    private RelationUser_ID_Mail_Repository relationUser_id_mail_repository;
    public void relationwite(UserForm userForm,MailForm mailForm){
        relationUser_id_mail_repository.relationwrite(userForm,mailForm);
    };

}
