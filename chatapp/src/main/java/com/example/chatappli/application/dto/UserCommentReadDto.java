package com.example.chatappli.application.dto;

import com.example.chatappli.domain.model.UserComment;
import com.example.chatappli.domain.model.UserComments;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserCommentReadDto {
	private final int id;
	private final String userId;
	private final String name;
	private final String mailAddress;
	private final String comment;
	private final LocalDateTime createdAt;
/**
 * RestAPIのレスポンス用変換
 * @param
 * @return
 */
 public static List<UserCommentReadDto> from(UserComments comments){
	 return comments.getValues().stream().map(
			 comment -> new UserCommentReadDto(
					 comment.getId(),
					 comment.getUserId().toString(),
					 comment.getName().toString(),
					 comment.getMailAddress().toString(),
					 comment.getComment().toString(),
					 LocalDateTime.parse(comment.getDateTime().toString())
			 )
	 ).collect(Collectors.toUnmodifiableList());
 }
}