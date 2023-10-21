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
	@Insert("sql/insertUserComment.sql")//利用するSQLファイルを指す。
	void insert(@Param("dto") UserCommentDto dto);
	
	@Select("sql/selectUserComment.sql")//Se
	List<UserCommentReadDto> select();

	@Select("sql/selectMyComment.sql")
	List<UserCommentReadDto> selectById(@Param("userId") String userId);
}
//SQLファイル増えたらMapperも増える。