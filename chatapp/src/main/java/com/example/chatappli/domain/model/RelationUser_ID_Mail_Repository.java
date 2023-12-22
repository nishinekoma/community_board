package com.example.chatappli.domain.model;

import com.example.chatappli.application.dto.MailReadDto;
import com.example.chatappli.application.form.CommentForm;
import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.domain.model.UserComment;
import com.example.chatappli.domain.model.UserCommentRepository;
import com.example.chatappli.domain.model.UserComments;
import com.example.chatappli.domain.model.UserId;
import org.springframework.security.core.userdetails.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RelationUser_ID_Mail_Repository {
    void relationwrite(UserForm userForm, MailForm mailForm);

    List<MailReadDto> selectByMail();
}
