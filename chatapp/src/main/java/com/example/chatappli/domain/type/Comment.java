package com.example.chatappli.domain.type;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)//private finalなフィールドについて、プライベートなコンストラクタを作成 このクラスでしかnewをできない
@EqualsAndHashCode
public class Comment {
	private final String value;
	
	public static Comment from(String comment) {//このクラスでしかnewできなく、このメソッドをしかnewする方法をしらないというのをファクトリメソッドという。
		return new Comment(comment);
	}

	@Override
	public String toString() {
		return value;
	}
}
