package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.domain.model.UserComment;
import com.example.chatappli.application.dto.UserCommentDto;
import com.example.chatappli.domain.model.UserCommentRepository;
import com.example.chatappli.domain.model.UserComments;
import com.example.chatappli.application.dto.UserCommentReadDto;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserCommentDatasource implements UserCommentRepository{
	private final UserCommentMapper mapper;
	
	public void save(UserComment userComment) {
		//パーツをつなげる。
		mapper.insert(UserCommentDto.from(userComment));//UserCommentMapper mapperをDI（依存性注入）するように指定します。
	}
	
	@Override
	public UserComments select() {
		List<UserCommentReadDto> dtos = mapper.select();
		return UserComments.from(
				dtos.stream().map(dto -> /*{ return*/ UserComments.UserComment.from(
						dto.getId(),
						dto.getName(),
						dto.getMailAddress(),
						dto.getComment(),
						dto.getCreatedAt()
						)/*;}*/).collect(Collectors.toUnmodifiableList())
				);
	}
	

	/*　UserComment.from(List<UserComment>の型。)
	 * dtos.stream()で　streamに変換する
	 * map(Function<? super T,? extends R> mapper)
	 * Function<T,R>T型を受け取りR型をかえす。
	 * mapメソッドを呼び出す　dtoのStream内の各要素からUserCommentReadDtoインスタンスdtoに
	 * https://qiita.com/GinGinDako/items/5b5fbc8992fbd4e85b5a
	 * */

}
//このようにしておけば、SpringがMyBatisの提供する機能を詰め込んでくれます。
/*
 * <UserComment> Stream<UserComment> java.util.stream.Stream.map
 * 	(Function<? super UserCommentReadDto, ? extends UserComment> mapper)*/
 