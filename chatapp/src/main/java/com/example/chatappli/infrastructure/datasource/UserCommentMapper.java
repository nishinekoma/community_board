package com.example.chatappli.infrastructure.datasource;

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

	/* また後で
	@Select("selectUserName.sql")//mdata.splのAUTHORITIES元。
	List<UserCommentReadDto> selectId(@Param("userId") String userId);
	 */
	//個人　USER_COMMENTテーブルのUSER_COMMENTにデータをぶち込む
	//signupから取ってくる。
	@Insert("sql/insertMailAddress.sql")//利用するSQLファイルを指す。mailaddressのみ取得
	void insertmail(@Param("dto") UserForm dto);

	//User_IDとmailAddressを入れる。 個人
	@Insert("sql/insertRelationUserId_Mail.sql")
	void relationwite(@Param("dto") UserForm dto);//これに変換する

}
//SQLファイル増えたらMapperも増える。