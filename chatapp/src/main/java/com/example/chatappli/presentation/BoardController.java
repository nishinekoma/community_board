package com.example.chatappli.presentation;

import com.example.chatappli.application.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;				//HTML(View層)にデータを渡す場合、利用するクラス

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import com.example.chatappli.application.usecase.UserCommentUseCase;
import com.example.chatappli.domain.model.UserComments;
@Controller//
@RequiredArgsConstructor//➀final修飾子を探してかつ初期化されていないものを探す。でpublic クラス名(➀に合致したもの　変数名){this.変数名 = 変数名}
public class BoardController {
	private final UserCommentUseCase userCommentUseCase;

	/**
	 *	メソッド名（引数）
	 *		method viewBord(ModelAndView modelAndView)
	 *	説明
	 *		データを読みboard.htmlにデータを渡す用のメソッド。
	 *	メソッド内説明
	 *		//board.html 26行目${comments}にして属性名を指定する(htmlでタイムリーフ使用してデータ連携)　
	 *		addOject("comments",userComments.getValues())
	 *
	 *		//HTMLfileのパス設定を行う。
	 *		modelAndView.setViewName("board");
	 *
	 *		//addObjectはThymeleafにデータを渡している。 コメントに書いた時のデータ
	 *		modelAndView.addObject("commentForm",new CommentForm());
	 *
	 * @param modelAndView  型ModelAndView　受け取るためDIされたもの？
	 * @return modelAndView コメントデータ類が追加された　型ModelAndView
	 *
	 * */
	@GetMapping("/board")
	public ModelAndView viewBord(ModelAndView modelAndView) {	//HTML(View層)にデータを渡す場合、利用するクラス
		//return repository(DI form(BoardContraller or UserApiController)からされた UserCommentRepository).select();
		UserComments userComments = userCommentUseCase.read();
		int i = 0;
		System.out.println(userComments.getValues()+"表示 :" + i);i++;
		//board.html 26行目${comments}にして属性名を指定する(htmlでタイムリーフ使用してデータ連携)　thread.同様である。
		modelAndView.addObject("comments",userComments.getValues());
		
		modelAndView.setViewName("board");//	HTMLfileのパス設定を行う。
		//フォーム初期用オブジェクトを最初に作る.
		// @GetMapping("/board")だから/boardにアクセスあった時に取得する
		modelAndView.addObject("commentForm",new CommentForm());//addObjectはThymeleafにデータを渡している。
		return modelAndView;
	}
	//ポストを受け取った時にバリデーションを動作させる。
	@PostMapping("/board")
	//@AuthenticationPrincipal 認証中（ログイン中のユーザ情報取得）
	public ModelAndView PostComment(@AuthenticationPrincipal User user, @Validated @ModelAttribute CommentForm comment, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {//bindingResult.hasErrors()は検証で一つでもエラーがあればbooleanの値を返す。　エラーがあった場合ture.　
			ModelAndView modelAndView = new ModelAndView("/board");
			modelAndView.addObject("commentForm",comment);//モデルに属性を追加する。
			return modelAndView;
		}
		//エラーが無ければ保存する
		userCommentUseCase.write(comment,user);
		return new ModelAndView("redirect:/board");//return "board"からreturn "redirect:/board";変更して　２重通信を回避。リロードしてもGETメソッドになる。
	}
}