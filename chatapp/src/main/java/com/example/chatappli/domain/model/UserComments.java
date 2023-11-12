package com.example.chatappli.domain.model;

import com.example.chatappli.domain.type.Comment;
import com.example.chatappli.domain.type.DateTime;
import com.example.chatappli.domain.type.MailAddress;
import com.example.chatappli.domain.type.Name;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)//コンストラクタ　フィールド設定
@Getter//クラス内にあるフィールド全てにゲッターが設定される。
public class UserComments {
	private final List<UserComment> values;//@Getter
	
	public static UserComments from(List<UserComment> comments) {
		if(CollectionUtils.isEmpty(comments)) return new UserComments(Collections.emptyList());
		return new UserComments(comments);
	}
	  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	  @Getter
	  public static class UserComment {
	    private final int id;
		private final UserId userId;
	    private final Name name;
	    private final MailAddress mailAddress;
	    private final Comment comment;
	    private final DateTime dateTime;

	    public static UserComment from(
	        int id,
			String userId,
	        String name,
	        String mailAddress,
	        String comment,
	        LocalDateTime dateTime) {
	      return new UserComment(
	          id,
			  UserId.form(userId),
	          Name.from(name),
	          MailAddress.from(mailAddress),
	          Comment.from(comment),
	          DateTime.from(dateTime)
	      );
	    }
	}
}
//DTO交換先