package com.example.chatappli.presentation;

import com.example.chatappli.application.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;				//HTML(View層)にデータを渡す場合、利用するクラス

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import com.example.chatappli.application.usecase.UserCommentUseCase;
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final UserCommentUseCase userCommentUseCase;
	
	
	@GetMapping("/board")
	public ModelAndView viewBord(ModelAndView modelAndView) {	//HTML(View層)にデータを渡す場合、利用するクラス
		modelAndView.setViewName("board");//	HTMLfileのパス設定を行う。
		modelAndView.addObject("commentForm",new CommentForm());//addObjectはThymeleafにデータを渡している。
		return modelAndView;
	}
	//ポストを受け取った時にバリデーションを動作させる。
	@PostMapping("/board")
	public ModelAndView PostComment(@Validated @ModelAttribute CommentForm comment,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {//bindingResult.hasErrors()は検証で一つでもエラーがあればbooleanの値を返す。　エラーがあった場合ture.　
			ModelAndView modelAndView = new ModelAndView("/board");
			modelAndView.addObject("commentForm",comment);//モデルに属性を追加する。
			return modelAndView;
		}
		//エラーが無ければ保存する
		userCommentUseCase.write(comment);
		return new ModelAndView("redirect:/board");//return "board"からreturn "redirect:/board";変更して　２重通信を回避。リロードしてもGETメソッドになる。
	}
}
