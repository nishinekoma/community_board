package com.example.chatappli.presentation;

import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.application.usecase.UserAuthUsecase;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    /**
     * 登録ページの表示
     * @return
     */
    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView modelAndView){
        modelAndView.setViewName("user/signup");
        modelAndView.addObject("userForm", new UserForm());

        return modelAndView;
    }
    /**
     * ユーザの登録処理
     * @param userForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    public ModelAndView register(
            @Validated @ModelAttribute UserForm userForm,
            BindingResult bindingResult,
            HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("userForm", userForm);
            return modelAndView;
        }
        try {
            userAuthUsecase.userCreate(userForm, request);
        }catch (Exception e) {
            log.error("ユーザ作成 or ログイン失敗", e);
            return new ModelAndView("redirect:/user");
        }
        //TODO: ユーザ作成処理
        return new ModelAndView("redirect:/board");
    }
}