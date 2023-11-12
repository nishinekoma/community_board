package com.example.chatappli.application.usecase;

import com.example.chatappli.application.form.CommentForm;
import com.example.chatappli.domain.model.UserComment;
import com.example.chatappli.domain.model.UserCommentRepository;
import com.example.chatappli.domain.model.UserComments;
import com.example.chatappli.domain.model.UserId;
import org.springframework.security.core.userdetails.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

//ユーザに投稿したデータをDBをに書き込むというユースケースをここで表現していく。　ビジネスロジック（システム固有の処理　データベースに入れるデータ準備等）
//ビジネスロジックとしてやることがあればここに書き込んでいく。
//直近投稿されたものと同じ投稿は受け入れない、というユースケースは複数のデータを見ないとわからいない
@Service//Springコンテナに定義（一つの部品として定義）
@RequiredArgsConstructor//フィールドを初期化するコンストラクタの定義
public class UserCommentUseCase {
	@Autowired //Autowiredにより実装したクラスがDIされる。 明示的にはいいと思う。DIされるって意味で　なくても稼働する
	private final UserCommentRepository repository;// DIされるのは UserCommentDatasource
	/*@Autowired　が生成するコード　ただし今回は@RequiredConstructorによって自然に生成されるため@Autowiredは本当ははいらない。
	* public UserCommentUseCase(UserCommentRepository repository) {
	* 		this.repository = repository
	* 	}
	*/
	/*
	 * ユーザの書き込みをDBに反映し、表示するデータをプレゼンテーション層に渡す
	 * @param commentForm ユーザ入力データ
	 * @return 表示するデータ
	*/
	public void write(CommentForm commentForm, User user) {
		//フォームアブジェクトからドメインオブジェクトへ変換
		UserComment userComment = UserComment.from(
				commentForm.getName(),
				user.getUsername(),
				commentForm.getMailAddress(),
				commentForm.getComment()
		);
		//例えばここで、直近の投稿の一覧を取得し、今回と同じ内容の投稿がないかチェックする。
		repository.save(userComment);//
	}
	 /**
	   * 投稿の取得
	   * @return 投稿のリスト
	   */
	public UserComments read(UserId userId) {
		return repository.select(userId);
	}
	public UserComments read() {
		return repository.select();
	}
}
/*DI Dependency Injection
	ChatappliのUserCommentUseCaseクラスのrepositoryに焦点を
	当てて47・48行目にブレイクポイント当てるとわかりやすい。実装されたクラスが代入されている。

* */


