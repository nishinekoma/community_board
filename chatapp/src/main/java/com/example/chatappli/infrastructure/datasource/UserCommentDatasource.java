package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.domain.model.UserComment;
import com.example.chatappli.application.dto.UserCommentDto;
import com.example.chatappli.domain.model.UserCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserCommentDatasource implements UserCommentRepository{
	private final UserCommentMapper mapper;
	@Override
	public void save(UserComment userComment) {
		//パーツをつなげる。
		mapper.insert(UserCommentDto.from(userComment));//UserCommentMapper mapperをDI（依存性注入）するように指定します。
	}
}
//このようにしておけば、SpringがMyBatisの提供する機能を詰め込んでくれます。