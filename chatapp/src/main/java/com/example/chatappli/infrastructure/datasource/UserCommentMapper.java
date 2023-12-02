package com.example.chatappli.infrastructure.datasource;

import com.example.chatappli.application.dto.UserCommentDto;
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
	@Select("selectUserName.sql")//data.splのAUTHORITIES元。
	List<UserCommentReadDto> selectId(@Param("userId") String userId);
	 */
}
//SQLファイル増えたらMapperも増える。