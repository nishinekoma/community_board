package com.example.chatappli.domain.model;

import com.example.chatappli.application.dto.UserCommentDto;
import com.example.chatappli.application.form.UserForm;

public interface UserCommentRepository {
	void save(UserComment userComment);

	void savemail(UserForm userForm);
	UserComments select();
	UserComments select(UserId userId);
	/*また後で
	UserComments selectID(UserId userId);
	 */
}
//Repositoryは永続化の抽象。　ので具体的な実装ではなくインターフェイスとして実現される。