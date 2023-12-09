package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.application.dto.RelationDto;
import com.example.chatappli.application.dto.UserCommentDto;
import com.example.chatappli.application.form.UserForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.chatappli.application.dto.UserCommentReadDto;
import java.util.List;

@Mapper//MyBatisのMapperは実行SQLファイルとJavaコードのマッピングに使用される。
public interface UserCommentMapper {
	@Insert("sql/insertUserComment.sql")//利用するSQLファイルを指す。Table USER_COMMENTから全て取得する
	void insert(@Param("dto") UserCommentDto dto);

	@Select("sql/selectUserComment.sql")//data.splのUSER_COMMENT元
	List<UserCommentReadDto> select();

	@Select("sql/selectMyComment.sql")//data.splのUSER_COMMENTもと。 結果UserApiControllerによって使われている
	List<UserCommentReadDto> selectById(@Param("userId") String userId);


	//User_IDとmailAddressを入れる。 個人
	@Insert("sql/insertRelationUserId_Mail.sql")
	void relationwite(@Param("dto") RelationDto dto);//これに変換する

}
//SQLファイル増えたらMapperも増える。