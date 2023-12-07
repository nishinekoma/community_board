package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.application.form.MailForm;
import com.example.chatappli.application.form.UserForm;
import com.example.chatappli.domain.model.*;
import com.example.chatappli.application.dto.UserCommentDto;
import com.example.chatappli.application.dto.UserCommentReadDto;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserCommentDatasource implements UserCommentRepository, RelationUser_ID_Mail_Repository {
	private final UserCommentMapper mapper;

	//Table USER_COMMENTから全て取得する
	//@Insert("sql/insertUserComment.sql")
	public void save(UserComment userComment) {	//this method used UserCommentUseCase
		//パーツをつなげる。
		mapper.insert(UserCommentDto.from(userComment));//UserCommentMapper mapperをDI（依存性注入）するように指定します。
	}
	//個人　mail初期化用　mail
	public void savemail(UserForm userForm){
		mapper.insertmail(userForm);
	}
	//UserIdとMailアドレスをデータに入れる
	@Override
	public void relationwrite(UserForm userForm, MailForm mailForm){
		//userForm.setMailAddress(mailForm.getMailAddress());
		mapper.relationwite(userForm);//mailaddressを織り込み済み
	}

	@Override
	public UserComments select() {
		List<UserCommentReadDto> dtos = mapper.select();
		return convert(dtos);
	}
	@Override
	public UserComments select(UserId userId){
		//@Select("sql/selectMyComment.sql")//data.splのUSER_COMMENTもと。
		//List<UserCommentReadDto> selectById(@Param("userId") String userId);
		List<UserCommentReadDto> dtos = mapper.selectById(userId.toString());
		return  convert(dtos);
	}
	/* また後で
	@Override
	public UserComments selectID(UserId userID){ //from @Select("selectUserName.sql")
		List<UserCommentReadDto> dtos = mapper.selectId(userID.toString());
		return convert(dtos);
	}
	 */

	public UserComments convert(List<UserCommentReadDto> dtos) {//convert変換するの意味
		return UserComments.from(
				dtos.stream().map(dto -> /*{ return*/ UserComments.UserComment.from(
						dto.getId(),
						dto.getUserId(),
						dto.getName(),
						dto.getMailAddress(),
						dto.getComment(),
						dto.getCreatedAt()
						)).collect(Collectors.toUnmodifiableList())
				);
	}
	//すべてUserCommentMapper(UserCommentReadDto)からUserCommentにconvertしている。

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
 * 	(Function<? super UserCommentReadDto ? extends UserComment> mapper)
 * */
 