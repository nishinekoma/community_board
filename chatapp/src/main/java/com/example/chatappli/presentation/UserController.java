package com.example.chatappli.presentation;

import ch.qos.logback.core.model.Model;
import com.example.chatappli.application.form.CommentForm;
import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.application.usecase.RelationUserID_Mail;
import com.example.chatappli.application.usecase.UserAuthUsecase;
import com.example.chatappli.application.usecase.UserCommentUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserAuthUsecase userAuthUsecase;
    private final UserCommentUseCase userCommentUseCase;
    //個人
    private final RelationUserID_Mail relationUserID_mail;

    @GetMapping
    public ModelAndView loginPage(ModelAndView modelAndView){
        modelAndView.setViewName("user/login");
        modelAndView.addObject("userForm",new UserForm());
        modelAndView.addObject("mailForm",new MailForm());
        return modelAndView;
    }

    /**
     * 登録ページの表示
     * @return
     */
    @GetMapping("signup")
    public ModelAndView signup(ModelAndView modelAndView){
        modelAndView.setViewName("user/signup");
        modelAndView.addObject("userForm", new UserForm());
        //メアドForm
        modelAndView.addObject("mailForm",new MailForm());
        return modelAndView;
    }
    /**
     * ユーザの登録処理
     * @param userForm
     * @param bindingResult
     * @return
     */
    // BindingResultを二つ入れるのは一つのインスタンスにつき一つしか対応できないため。
    @PostMapping("signup")//ここから登録受け取り　htmlから受け取っている。
    public ModelAndView register(
            @Validated @ModelAttribute("userForm") UserForm userForm,
            BindingResult bindingResult,
            @Validated @Valid @ModelAttribute("mailForm") MailForm mailForm,
            BindingResult bindingResult1,
            HttpServletRequest request) {
        //ここはフロントの処理を書くべき
        if(bindingResult.hasErrors() || bindingResult1.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/user/login");
            modelAndView.addObject("userForm",userForm);//エラー時の元の情報を残すために返す。
            modelAndView.addObject("mailForm",mailForm);
            //ログに出す用。
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.error("Validation error: {}", error.getDefaultMessage());
            }
            return modelAndView;
        }
        //ここのtryにはバックエンドの処理を書くべき
        try {
            //debug
            System.out.println("username: " + userForm.getUsername()+ " mail"+ mailForm.getMailAddress());
            SecurityContextHolder.clearContext();//既存の認証情報をクリア
            userAuthUsecase.userCreate(userForm, request);

            //入力されたmailとUser_IDを紐づけ
            relationUserID_mail.relationwite(userForm, mailForm);
            /*
            //重複処理
            if(uniqueEmailValidator.isValid(mailForm.getMailAddress(),null)) {
                //その時入力されたメアドをCommetFormに登録したい処理
                //userCommentUseCase.write(userForm);
                //入力されたmailとUser_IDを紐づけ
                relationUserID_mail.relationwite(userForm, mailForm);
             */
        }catch (Exception e) {
            log.error("ユーザ作成 or ログイン失敗", e);
            return new ModelAndView("redirect:/user");
        }
        //TODO: ユーザ作成処理
        return new ModelAndView("redirect:/board");
    }
}
