package com.example.chatappli.application.dto;

import com.example.chatappli.domain.model.UserComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)			//lombok.RequiredArgsConstructor
@Getter								//lombok.Getter
public class UserCommentDto {
	private final String name;
	private final String mailAddress;
	private final String comment;
	
	public static UserCommentDto from(UserComment usercomment) {//UserComment usercommentは受け取っているだけでインスタンスの生成はしていない。
		return new UserCommentDto(
				usercomment.getName().toString(),
				usercomment.getMailAddress().toString(),
				usercomment.getComment().toString()
				);
		}
}
