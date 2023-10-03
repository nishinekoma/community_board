package com.example.chatappli.domain.model;

import com.example.chatappli.application.dto.UserCommentDto;

public interface UserCommentRepository {
	void save(UserComment userComment);
	UserComments select();
	UserComments select(UserId userId);
}
//Repositoryは永続化の抽象。　ので具体的な実装ではなくインターフェイスとして実現される。