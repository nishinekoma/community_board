package com.example.chatappli.domain.model;


public interface UserCommentRepository {
	void save(UserComment userComment);
}
//Repositoryは永続化の抽象。　ので具体的な実装ではなくインターフェイスとして実現される。